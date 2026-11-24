package com.example.myresumeapp.components

import android.R
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myresumeapp.data.ResumeData


@Composable
fun  ResumeCard(resume: ResumeData,modifier: Modifier = Modifier){
    Card(modifier = modifier, shape = MaterialTheme.shapes.large, elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)) {

        Column(Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)){
                AsyncImage(model = resume.personal.photoUrl.ifEmpty { null },
                    contentDescription = "profile photo", modifier = Modifier.size(96.dp), contentScale = ContentScale.Crop
                    )
              Column(Modifier.weight(1f)) {
                  Text(resume.personal.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
Text(resume.personal.email,style = MaterialTheme.typography.bodyMedium)
                  Text(resume.personal.phone,style = MaterialTheme.typography.bodyMedium)
                  Text(resume.personal.phone2,style = MaterialTheme.typography.bodyMedium)

                  Text(resume.personal.location,style = MaterialTheme.typography.bodyMedium)
              }
              }

            Spacer(Modifier.height(12.dp))
            HorizontalDivider()
            Text(text = "Qualification", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
Text(text = "${resume.qualificationSkills.degree} . ${resume.qualificationSkills.institution}")
            Text("${resume.qualificationSkills.startYear} - ${resume.qualificationSkills.endYear}")
            if (resume.qualificationSkills.skills.isNotBlank()){
                Spacer(Modifier.height(12.dp))
                HorizontalDivider()
                Spacer(Modifier.height(12.dp))
                Text(text = "Skills", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Text(text = resume.qualificationSkills.skills)
            }
            Spacer(Modifier.height(12.dp))
            HorizontalDivider()
            Spacer(Modifier.height(12.dp))
            Text(text = "Experience", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
Text(text = "${resume.experience.company} . ${resume.experience.experienceYears}")
            Text(resume.experience.role)
            Text(resume.experience.responsibilities.ifEmpty { "-" })



            }

        }

    }
