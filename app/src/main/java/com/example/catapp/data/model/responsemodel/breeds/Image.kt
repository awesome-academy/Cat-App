package com.example.catapp.data.model.responsemodel.breeds

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val id: String,
    val url: String,
): Parcelable
