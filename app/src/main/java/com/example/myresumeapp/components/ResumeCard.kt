package com.example.myresumeapp.components

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myresumeapp.data.ResumeData


@Composable
fun  ResumeCard(resume: ResumeData,modifier: Modifier = Modifier){
    Card(modifier = modifier, shape = MaterialTheme.shapes.large, elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)) {

    }
}