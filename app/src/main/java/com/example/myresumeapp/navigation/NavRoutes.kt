package com.example.myresumeapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myresumeapp.uiscreens.ExperienceScreen
import com.example.myresumeapp.uiscreens.PersonalScreen
import com.example.myresumeapp.uiscreens.PreviewScreen
import com.example.myresumeapp.uiscreens.QualificationScreen
import com.example.myresumeapp.viewmodel.ResumeViewModel


object Routes {
    const val  PESRONAL = "personal"
    const val QUALIFICATION = "qualification"
    const val EXPERIENCE = "experience"
    const val PREVIEW = "preview"

}

@Composable
fun AppNavigation(navController: NavHostController= rememberNavController()) {
    val resumeViewModel: ResumeViewModel = viewModel()
    NavHost(navController, startDestination = Routes.PESRONAL) {
        composable(Routes.PESRONAL) {
            PersonalScreen(
                uiState = resumeViewModel.uiState,
                onNext = { navController.navigate(Routes.QUALIFICATION) },
                onPhotoPicked = resumeViewModel::setPghoto,
                onFieldChanged = resumeViewModel::updatePersonal
            )
        }
        composable(Routes.QUALIFICATION) {
            QualificationScreen(
                uiState = resumeViewModel.uiState,
                onNext = { navController.navigate(Routes.EXPERIENCE) },
                onPrev = { navController.popBackStack() },
                onFieldChange = resumeViewModel::updateQualification
            )

        }
        composable(Routes.EXPERIENCE) {
            ExperienceScreen(
                uiState = resumeViewModel.uiState,
                onPrev = { navController.popBackStack() },
                onNext = { navController.navigate(Routes.PREVIEW) },
                onFielChange = resumeViewModel::updateExperience
            )

        }
        composable(Routes.PREVIEW) {


            }
        }


    }
