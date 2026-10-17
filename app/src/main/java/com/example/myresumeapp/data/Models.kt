package com.example.myresumeapp.data

import android.net.Uri

data class PersonalDetails(val name: String="",
                           val email: String="",
                           val phone: String="",
                           val location: String="",
                           val photoUrl: String="")


data class QualificationSkills(
    val degree: String = "",
    val institution: String = "",
    val startYear: String = "",
    val endYear: String = "",
    val skills: String = "",
)


data class Experience(
    val responsibilities: String = "",
    val experienceYears: String = "",
    val company: String = "",
    val role: String = "",
)

data class ResumeData(
    val id: String = "",
    val personal: PersonalDetails = PersonalDetails(),
    val qualificationSkills: QualificationSkills = QualificationSkills(),
    val experience: Experience = Experience(),
)

data class UiState(
    val resume: ResumeData = ResumeData(),
    val localPhotoUri: Uri? = null,
    val isSaving: Boolean = false,
    val lastSavedId: String? = null,
    val message: String? = null
)

