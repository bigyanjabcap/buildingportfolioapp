package com.example.myresumeapp.data
/*
import android.R.attr.id
import android.graphics.Bitmap
import android.util.Log
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
        return updated
    }
    suspend fun fetch(id: String): ResumeData?{
val resumedata = firebaseDatabase.child("users/$userId/resumes/$id").get().await()
        return resumedata.getValue(ResumeData::class.java)
    }


    suspend fun delete(id: String) {
        firebaseDatabase.child("users/$userId/resumes/$id").removeValue()
        suspend fun delete(id: String) {
            firebaseDatabase.child("users/$userId/resumes/$id").removeValue()
            runCatching {
                firebaseStorage
                    .from("uploadss") // your bucket name
                    .delete("users/$userId/$id/photo.jpg")
            }.onFailure { Log.e("Delete", "Storage delete failed", it) }
        }
    }

    }

 */


import android.graphics.Bitmap
import android.util.Log
import com.example.myresumeapp.SupabaseClient
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.time.withTimeout
import kotlinx.coroutines.withTimeout
import java.io.ByteArrayOutputStream
import java.util.UUID

class ResumeRepository {
    private val firebaseDatabase: DatabaseReference = Firebase.database("https://myresumeapp1-4cc28-default-rtdb.firebaseio.com/").reference
    private val supabaseStorage = SupabaseClient.client.storage
    private val bucketName = "uploadss"
    private val userId: String get() = Firebase.auth.currentUser?.uid ?: "guest"

    suspend fun createOrUpdate(resume: ResumeData, localPhotoBitMap: Bitmap?): ResumeData {
        Log.d("SAVE", "1. createOrUpdate started, userId=$userId")

        val id = resume.id.ifEmpty {
            firebaseDatabase.child("users/$userId/resumes").push().key
                ?: UUID.randomUUID().toString()
        }
        Log.d("SAVE", "2. resume id=$id")
        var photoUrl = resume.personal.photoUrl

        if (localPhotoBitMap != null) {
            val bytes = ByteArrayOutputStream().use { out ->
                localPhotoBitMap.compress(Bitmap.CompressFormat.JPEG, 92, out)
                out.toByteArray()
            }
            val photoPath = "users/$userId/$id/photo.jpg"

            supabaseStorage.from(bucketName).upload(
                path = photoPath,
                data = bytes
            ) {
                upsert = true
            }
            photoUrl = supabaseStorage.from(bucketName).publicUrl(photoPath)
            Log.d("SAVE", "3. Supabase upload done, url=$photoUrl")
        }
        else{
            Log.d("SAVE", "3. No photo to upload")

        }

        val updated = resume.copy(id = id, personal = resume.personal.copy(photoUrl = photoUrl))
        Log.d("SAVE", "4. Writing to RTDB: users/$userId/resumes/$id  data=$updated")
        withTimeout(10_000) {
            firebaseDatabase.child("users/$userId/resumes/$id").setValue(updated).await()
        }
        return updated
    }

    suspend fun fetch(id: String): ResumeData? {
        val snapshot = firebaseDatabase.child("users/$userId/resumes/$id").get().await()
        return snapshot.getValue(ResumeData::class.java)
    }

    suspend fun delete(id: String) {
        firebaseDatabase.child("users/$userId/resumes/$id").removeValue().await()

        runCatching {
            supabaseStorage
                .from(bucketName)
                .delete("users/$userId/$id/photo.jpg")
        }.onFailure { Log.e("Delete", "Storage delete failed", it) }
    }
}