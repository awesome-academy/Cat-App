package com.example.catapp.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.catapp.R

fun View.isGone() {
    visibility = View.GONE
}

fun View.isVisible() {
    visibility = View.VISIBLE
}

fun View.isInvisible() {
    visibility = View.INVISIBLE
}

fun View.disable(context: Context) {
    backgroundTintList =
        ContextCompat.getColorStateList(context, R.color.color_shuttle_grey)
    isClickable = false
}

fun View.enable() {
    backgroundTintList =
        ContextCompat.getColorStateList(context, R.color.color_pink)
    isClickable = true
}
