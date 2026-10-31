package com.example.myresumeapp.uiscreens

import android.net.Uri
import android.provider.CalendarContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myresumeapp.R
import com.example.myresumeapp.data.UiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalScreen(uiState: UiState,onNext:()-> Unit,onPhotoPicked:(Uri?)-> Unit,onFieldChanged:(String?, String?, String?, String?)-> Unit){

    val photpicked = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        onPhotoPicked(uri)
    }
    Scaffold(topBar ={TopAppBar(title = { Text("My resume", color = Color.White) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue))}) { paddingValues ->
        Column(Modifier.padding(paddingValues).padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Personal Details", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(6.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AsyncImage(model = uiState.localPhotoUri ?:R.drawable.outline_animated_images_24, contentDescription = "Selected Photo",
                    modifier = Modifier.size(96.dp).clip(CircleShape))

                Button(onClick = {photpicked.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))}, colors = ButtonDefaults.buttonColors(
                    Color.Blue)) {
            Text(if (uiState.localPhotoUri==null) "Select Photo" else "Change Photo")
                }
            //this launches the photo gallery {

                }
            }
        }

    }

