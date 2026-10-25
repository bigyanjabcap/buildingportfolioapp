package com.example.myresumeapp.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myresumeapp.data.ResumeRepository
import com.example.myresumeapp.data.UiState
import com.example.myresumeapp.utils.toSoftwareBitmap
import io.ktor.http.ContentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response
import kotlin.String

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

    fun updatePersonal(name: String?,email: String?,phone:String?,location: String?){
        val currentResume = internalUiState.value.resume
        internalUiState.value=internalUiState.value.copy(
            resume = currentResume.copy(
                personal = currentResume.personal.copy(name = name?:currentResume.personal.name,
email = email?:currentResume.personal.email, phone = phone?:currentResume.personal.phone, location = location?:currentResume.personal.location
                    )
            )
        )
    }
    fun updateQualification(degree: String?,institution:String?,startYear:String?,endYear:String?,skills:String?){
val currentResume=internalUiState.value.resume
        internalUiState.value=internalUiState.value.copy(
            resume = currentResume.copy(
qualificationSkills = currentResume.qualificationSkills.copy(
    degree = degree?:currentResume.qualificationSkills.degree,institution=institution?:currentResume.qualificationSkills.institution,
    startYear=startYear?:currentResume.qualificationSkills.startYear,
    endYear=endYear?:currentResume.qualificationSkills.endYear,
    skills = skills?:currentResume.qualificationSkills.skills
)
            )
        )

    }
    fun updateExperience(
     responsibilities: String?,
     experienceYears: String?,
     company: String?,
     role: String?){

val currentResume = internalUiState.value.resume
        internalUiState.value = internalUiState.value.copy(
            resume = currentResume.copy(
                experience = currentResume.experience.copy(
                    responsibilities = responsibilities?:currentResume.experience.responsibilities,
                    company = company?:currentResume.experience.company,
                    role = role?:currentResume.experience.role,
                    experienceYears = experienceYears?:currentResume.experience.experienceYears,
                    )
            )
        )
    }
    fun delete(onDone:()-> Unit){
val id= internalUiState.value.resume.id
        viewModelScope.launch {
internalUiState.value = internalUiState.value.copy(isSaving = true, message = null)
            runCatching {
                if (id.isNotEmpty()) resumeRepository.delete(id)
internalUiState.value= UiState()
                onDone()
            }.onFailure {
                internalUiState.value = internalUiState.value.copy(isSaving = false, message = "Delete failed ${it.message}")
            }


        }
    }
    fun saveToFirebase(){
        viewModelScope.launch {
            internalUiState.value  = internalUiState.value.copy(isSaving = true, message = null)
            val bitmap = withContext(Dispatchers.IO){localBitMapOrNull()}
            runCatching {
                resumeRepository.createOrUpdate(internalUiState.value.resume,bitmap)
            }.onSuccess { saved->
                internalUiState.value=internalUiState.value.copy(isSaving = false, resume = saved, message = "saved")
            }.onFailure {
                internalUiState.value = internalUiState.value.copy(isSaving = false, message = "Save message ${it.message} ")
            }

        }
    }
    fun downloadPDF(uri:Uri?){
        if(uri==null) return
        viewModelScope.launch(Dispatchers.IO){
            runCatching {
                val bitmap = localBitMapOrNull()
                val  appContext = getApplication<Application>()
                //generate  resumw pdf
            }.onSuccess {
                withContext(Dispatchers.Main){
                    internalUiState.value=internalUiState.value.copy(message = "saved")
                }
            }.onFailure {
                withContext(Dispatchers.Main){
internalUiState.value=internalUiState.value.copy(message = "PDF saved failed ${it.message}")
                }
            }

        }

    }


    }
