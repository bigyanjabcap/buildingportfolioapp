package com.example.myresumeapp.data

import android.R.attr.id
import android.graphics.Bitmap
import com.example.myresumeapp.SupabaseClient
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.util.UUID

class ResumeRepository {
    private val firebaseDatabase: DatabaseReference = Firebase.database.reference
    private val firebaseStorage = SupabaseClient.client.storage
    private val userId: String get() = Firebase.auth.currentUser?.uid ?: "guest"
    suspend fun createOrUpdate(resume: ResumeData, localPhotoBitMap: Bitmap?): ResumeData {
        val id = resume.id.ifEmpty {
            firebaseDatabase.child("users/$userId/resumes").push().key ?: UUID.randomUUID()
                .toString()
        }
        var photoUrl = resume.personal.photoUrl
        if (localPhotoBitMap != null) {
            val bytes = ByteArrayOutputStream().use { out ->
                localPhotoBitMap.compress(Bitmap.CompressFormat.JPEG, 92, out)
                out.toByteArray()
            }
            val photoPath = "users/$userId/$id/photo.jpg"

            val photoStorage =// NEW SUPABASE CODE
                firebaseStorage.from("uploadss").upload(
                    path = photoPath,
                    data = bytes
                ) {
                    upsert = true // optional: overwrites if the file name already exists

                }
            photoUrl = firebaseStorage.from("uploads").publicUrl(photoPath)
        }
        val updated = resume.copy(id = id, personal = resume.personal.copy(photoUrl = photoUrl))
        firebaseDatabase.child("users/$userId/resumes/$id").setValue(updated).await()
    }


    }
