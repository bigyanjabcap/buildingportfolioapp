package com.example.myresumeapp





import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import io.github.jan.supabase.storage.upload
import java.io.File


object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://lrqadbyokbzgwrlujamy.supabase.co",
        supabaseKey = "sb_publishable_caVTmFLdQXNaswsqz5cgRA_BHSFh2bb"
    ) {
        install(Storage)
        // install(Auth) // Uncomment if you add Auth later
    }
}
suspend fun uploadMyFile(fileToUpload: File) {
    val bucket = SupabaseClient.client.storage.from("uploadss")
    bucket.upload(
        path = "folder/my_image.jpg",
        file = fileToUpload
    ) {
        upsert = false
    }
}