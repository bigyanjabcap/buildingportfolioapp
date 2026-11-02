package com.example.myresumeapp.uiscreens

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myresumeapp.data.UiState
import java.math.RoundingMode


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QualificationScreen(uiState: UiState,onNext:()->Unit,onPrev:()->Unit,
                        onFieldChange:(degree: String?,institution: String?,startYear: String?,endYear: String?,skills: String?)-> Unit){

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text("MyResume", color = Color.White) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
            , navigationIcon = { TextButton(onClick = onPrev){Text("Back", color = Color.White)} })
    }) { paddingValues ->

        Column(
            Modifier.padding(paddingValues).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Qualification and skills", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(6.dp))


            OutlinedTextField(
                value = uiState.resume.qualificationSkills.degree, onValueChange = {
                onFieldChange(
                    it,
                    null, null, null, null
                )
            }, label = { Text("Degree") },
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)
            )

            OutlinedTextField(
                value = uiState.resume.qualificationSkills.institution,
                onValueChange = {
                    onFieldChange(
                        null,
                        it, null, null, null
                    )
                },
                label = { Text("Institution") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            OutlinedTextField(
                value = uiState.resume.qualificationSkills.startYear,
                onValueChange = {
                    onFieldChange(
                        null,
                        null, it, null, null
                    )
                },
                label = { Text("Start year") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            OutlinedTextField(
                value = uiState.resume.qualificationSkills.endYear,
                onValueChange = { onFieldChange(null, null, null, it, null) },
                label = { Text("End year") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            OutlinedTextField(
                value = uiState.resume.qualificationSkills.skills,
                onValueChange = { onFieldChange(null, null, null, null, it) },
                label = { Text("Skills") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3,
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                Button(onClick = onNext, colors = ButtonDefaults.buttonColors(Color.Blue),
                    enabled = uiState.resume.personal.name.isNotEmpty() && uiState.resume.personal.email.isNotEmpty()) {
                    Text("Next")
                }
            }

        }

    }

                        }