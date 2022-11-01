package com.example.catapp.utils

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.catapp.R

fun Context.shortToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.disable(context: Context) {
    backgroundTintList =
        ContextCompat.getColorStateList(context, R.color.color_shuttle_grey)
    isClickable = false
}

fun View.enable() {
    isClickable = true
}
