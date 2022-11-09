package com.example.catapp.utils

import android.view.View
import android.widget.AdapterView

fun spinnerSelectedListener(onSelected: (p0: AdapterView<*>?, position: Int) -> Unit) =
    object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            onSelected(p0, p2)
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            //not used yet
        }
    }
