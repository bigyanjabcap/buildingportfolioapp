package com.example.myresumeapp.utils

import android.graphics.Bitmap

import android.os.Build

fun Bitmap.toSoftwareBitmap(): Bitmap {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
        this.config == Bitmap.Config.HARDWARE
    ) {
        this.copy(Bitmap.Config.ARGB_8888, false)
    } else {
        this
    }
}