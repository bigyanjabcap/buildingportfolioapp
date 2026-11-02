package com.example.myresumeapp.uiscreens

import android.R
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.myresumeapp.data.UiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QualificationScreen(uiState: UiState,onNext:()->Unit,onPrev:()->Unit,
                        onFieldChange:(degree: String?,institution: String?,startYear: String?,endYear: String?,skills: String)-> Unit){

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text("MyResume", color = Color.White) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
            , navigationIcon = { TextButton(onClick = onPrev){Text("Back", color = Color.White)} })
    }){ paddingValues ->


    }

                        }