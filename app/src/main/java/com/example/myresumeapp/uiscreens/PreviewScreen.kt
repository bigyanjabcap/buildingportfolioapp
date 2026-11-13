package com.example.myresumeapp.uiscreens

import android.R
import android.net.Uri
import android.widget.Button
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.example.myresumeapp.components.ResumeCard
import com.example.myresumeapp.data.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  PreviewScreen(uiState: UiState, onEdit:()-> Unit, onDelete:()-> Unit, onSaveDataBase:()-> Unit, onDownloadPdf:(Uri?)-> Unit) {


    //this variable lets the user to save the pdf onto the device
val createPdf = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/pdf")){ uri->
    onDownloadPdf(uri)
}
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text("MyResume", color = White) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
            , navigationIcon = { TextButton(onClick = onEdit){Text("Edit", color = White)} },
            actions = {TextButton(onClick = onDelete) {
                Text("Delete", color = White)
            }
                })
    }) { paddingValues ->

        Column(
            Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
Text("Resume Preview", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(6.dp))

            //Resume Card
            ResumeCard(resume = uiState.resume, modifier = Modifier.weight(1f))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                androidx.compose.material3.Button(onClick = onSaveDataBase, enabled = !uiState.isSaving, colors = ButtonDefaults.buttonColors(Color.Blue)){
                    Text(if (uiState.isSaving) "Saving..." else "Save to database", color = Color.White)
                }
                androidx.compose.material3.Button(onClick = {createPdf.launch("Resume_${uiState.resume.personal.name.ifBlank {  "My"}}.pdf")},
                    colors = ButtonDefaults.buttonColors(Color.Blue)){
Text("Downoad PDF", color = Color.White)
                }
            }
            uiState.message?.let {
                Text(it, color = Color.Red)
            }
        }

        }
    }
////dsvsdv