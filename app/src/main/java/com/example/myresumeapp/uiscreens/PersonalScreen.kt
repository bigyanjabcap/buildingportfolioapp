package com.example.myresumeapp.uiscreens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalScreen(uiState: UiState,onNext:()-> Unit,onPhotoPicked:(Uri?)-> Unit,onFieldChanged:(String?, String?, String?, String?, String?)-> Unit) {

    val photpicked =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            onPhotoPicked(uri)
        }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("My resume", color = Color.White) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
        )
    }) { paddingValues ->
        Column(
            Modifier.padding(paddingValues).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Personal Details", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(6.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = uiState.localPhotoUri ?: R.drawable.outline_animated_images_24,
                    contentDescription = "Selected Photo",
                    modifier = Modifier.size(96.dp).clip(CircleShape)
                )

                Button(
                    onClick = { photpicked.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                    colors = ButtonDefaults.buttonColors(
                        Color.Blue
                    )
                ) {
                    Text(if (uiState.localPhotoUri == null) "Select Photo" else "Change Photo")
                }
                //this launches the photo gallery {

            }

            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = uiState.resume.personal.name,
                onValueChange = { onFieldChanged(it, null, null, null,null) }, label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            OutlinedTextField(
                value = uiState.resume.personal.location,
                onValueChange = { onFieldChanged(null, it, null, null,null) },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                value = uiState.resume.personal.email,
                onValueChange = { onFieldChanged(null, null, it, null,null) }, label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )
            OutlinedTextField(
                value = uiState.resume.personal.phone,
                onValueChange = { onFieldChanged(null, null, null, it,null) }, label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            OutlinedTextField(
                value = uiState.resume.personal.phone2,
                onValueChange = { onFieldChanged(null, null, null, null,it) }, label = { Text("Phone2") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
Button(onClick = onNext, colors = ButtonDefaults.buttonColors(Color.Blue),
    enabled = uiState.resume.personal.name.isNotEmpty() && uiState.resume.personal.email.isNotEmpty()) {
    Text("Next")

}
            }

        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersonalScreenPreview() {
    MaterialTheme {
        PersonalScreen(
            uiState = UiState(), // see note below
            onNext = {},
            onPhotoPicked = {},
            onFieldChanged = { string, string1, string2, string3, string4
                -> }
        )
    }
}




