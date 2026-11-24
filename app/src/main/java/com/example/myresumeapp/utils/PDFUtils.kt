package com.example.myresumeapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.graphics.Typeface
import com.example.myresumeapp.data.ResumeData

fun generateResumePDFToUri(context: Context,resume: ResumeData,photo: Bitmap?,outputUri: Uri) {
    val pageWidth = 595
    val pageHeight =  842

    val pdf = android.graphics.pdf.PdfDocument()
    val pageInfo = android.graphics.pdf.PdfDocument.PageInfo.Builder(pageWidth,pageHeight,1).create()
    val page = pdf.startPage(pageInfo)
val canvas = page.canvas
val titlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { textSize = 20f; typeface = Typeface.create(Typeface.DEFAULT,Typeface.BOLD)  }
    val headerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { textSize = 14f; typeface = Typeface.create(Typeface.DEFAULT,Typeface.BOLD)  }
val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { textSize=12f; }
    val divider = Paint().apply { color=Color.LTGRAY;strokeWidth = 2f }

var y = 40f /// for phtootoo
    photo?.let {
        val safeBitmap = it.toSoftwareBitmap()
        val size =96
        val scaled = Bitmap.createScaledBitmap(safeBitmap,size,size,false)
        canvas.drawBitmap(scaled,(pageWidth - size - 40 ).toFloat(),y,null)


        //name
    }
    canvas.drawText(resume.personal.name,40f,y+24f,titlePaint)
    y+=44f
    canvas.drawText(resume.personal.email,40f,y,textPaint); y+=18f
    canvas.drawText(resume.personal.phone,40f,y,textPaint); y+=18f
    canvas.drawText(resume.personal.phone2,40f,y,textPaint); y+=18f
    canvas.drawText(resume.personal.location,40f,y,textPaint); y+=18f


    //now adding a divider to the page

    canvas.drawLine(40f,y,(pageWidth -40).toFloat(),y,divider);y+=24f


    //qualification

canvas.drawText("Qualification",40f,y,headerPaint); y+=20f
    canvas.drawText("${resume.qualificationSkills.degree} . ${resume.qualificationSkills.institution}",40f,y,textPaint); y+=18f;
    canvas.drawText("${resume.qualificationSkills.startYear} . ${resume.qualificationSkills.endYear}",40f,y,textPaint); y+=18f;


    canvas.drawLine(40f,y,(pageWidth -40).toFloat(),y,divider);y+=24f

    ///skilssssssssss

    canvas.drawText("Skills",40f,y,headerPaint); y+=20f
    if (resume.qualificationSkills.skills.isNotBlank()){
y =drawMultipleLine(canvas,resume.qualificationSkills.skills,40f,y,pageWidth-80,textPaint)
        y+=6f

    }

    //divider
    canvas.drawLine(40f,y,(pageWidth -40).toFloat(),y,divider);y+=24f

    //Experience

    canvas.drawText("Experience",40f,y,headerPaint); y+=20f
    canvas.drawText("${resume.experience.company} . ${resume.experience.experienceYears}",40f,y,textPaint); y+=18f;
    canvas.drawText(resume.experience.role,40f,y,textPaint); y+=18f;
    canvas.drawText(resume.experience.responsibilities,40f,y,textPaint); y+=18f;
    pdf.finishPage(page)
    context.contentResolver.openOutputStream(outputUri)?.use { out ->
        pdf.writeTo(out)

    }
    pdf.close()


}


//this is usd to print long paragraph
private fun drawMultipleLine(canvas: Canvas,text: String,x:Float,startY:Float,maxWidth:Int,paint: Paint): Float{
val words = text.split(Regex("\\s+"))
    var y = startY
    var line = ""
    for (w in words) {
        val trial = if (line.isEmpty()) w else "$line"

        if (paint.measureText(trial) > maxWidth) {
            canvas.drawText(line, x, y, paint)
            y += 16f
            line = w
        }
        else{
            line =trial
        }
    }

    if (line.isNotEmpty()){
canvas.drawText(line,x,y,paint)
        y+=16f
    }
    return y

}