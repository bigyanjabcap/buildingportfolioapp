package com.example.myresumeapp.uiscreens
/*
import androidx.compose.runtime.Composable
import com.example.myresumeapp.data.UiState
@Composable
fun ExperienceScreen(
    uiState: UiState, onPrev:()-> Unit, onNext:()-> Unit, onFielChange:(responsibility:String?,experienceYears:String?,company:String?,role: String?)-> Unit) {






}



 */

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
import com.example.myresumeapp.data.Experience
import com.example.myresumeapp.data.UiState
import java.math.RoundingMode


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceScreen(uiState: UiState,onNext:()->Unit,onPrev:()->Unit,
                        onFieldChange:(responsibilities: String?,
                                       experienceYears: String?,
                                       company: String?,
                                       role: String?)-> Unit){

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text("MyResume", color = Color.White) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
            , navigationIcon = { TextButton(onClick = onPrev){Text("Back", color = Color.White)} })
    }) { paddingValues ->

        Column(
            Modifier.padding(paddingValues).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Expereience", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(6.dp))


            OutlinedTextField(
                value = uiState.resume.experience.responsibilities, onValueChange = {
                    onFieldChange(
                        it,
                        null, null, null
                    )
                }, label = { Text("Responsibilities") },
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)
            )

            OutlinedTextField(
                value = uiState.resume.experience.experienceYears,
                onValueChange = {
                    onFieldChange(
                        null,
                        it, null, null
                    )
                },
                label = { Text("Experience Years") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            OutlinedTextField(
                value = uiState.resume.experience.company,
                onValueChange = {
                    onFieldChange(
                        null,
                        null, it, null,
                    )
                },
                label = { Text("Company name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            OutlinedTextField(
                value = uiState.resume.experience.role,
                onValueChange = { onFieldChange(null, null, null, it) },
                label = { Text("Role") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )
/*
            OutlinedTextField(
                value = uiState.resume.qualificationSkills.skills,
                onValueChange = { onFieldChange(null, null, null, null, it) },
                label = { Text("Skills") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3,
                shape = RoundedCornerShape(16.dp)
            )

 */
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                Button(onClick = onNext, colors = ButtonDefaults.buttonColors(Color.Blue)) {
                    Text("Finish")
                }
            }

        }

    }

}