package com.example.myresumeapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.net.Uri
import com.example.myresumeapp.data.ResumeData

fun generateResumePDFToUri(context: Context,resume: ResumeData,photo: Bitmap?,outputUri: Uri) {
    val pageWidth = 595
    val pageHeight =  842

    val pdf = android.graphics.pdf.PdfDocument()
    val pageInfo = android.graphics.pdf.PdfDocument.PageInfo.Builder(pageWidth,pageHeight,1).create()
    val page = pdf.startPage(pageInfo)
val canvas = page.canvas
    val titlepaint = Paint()
}