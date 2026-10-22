package com.example.myresumeapp.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.myresumeapp.data.ResumeRepository
import com.example.myresumeapp.data.UiState
import com.example.myresumeapp.utils.toSoftwareBitmap
import io.ktor.http.ContentType
import okhttp3.Response

class ResumeViewModel(app: Application) : AndroidViewModel(app){
private val resumeRepository = ResumeRepository()
    private val internalUiState  = mutableStateOf(UiState())
    val uiState: UiState get() = internalUiState.value



    fun setPghoto(uri: Uri?){
internalUiState.value = internalUiState.value.copy(localPhotoUri = uri)
    }
    private fun localBitMapOrNull(): Bitmap?{
        val appContext = getApplication<Application>()
        val uri = internalUiState.value.localPhotoUri?:return null
        return runCatching {
            val bitmap = if(Build.VERSION.SDK_INT>=28){
                val imageSource = ImageDecoder.createSource(appContext.contentResolver,uri)
                ImageDecoder.decodeBitmap(imageSource)
            }
            else{
                @Suppress("DEPRECATION")
                MediaStore.Images.Media.getBitmap(appContext.contentResolver,uri)
            }
            bitmap.toSoftwareBitmap()
        }.getOrNull()
    }

// thiis convert uri into pixelsssss picturee

    fun updatePersonal(){

    }
}