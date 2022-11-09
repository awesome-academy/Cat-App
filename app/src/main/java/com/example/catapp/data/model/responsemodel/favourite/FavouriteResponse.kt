package com.example.catapp.data.model.responsemodel.favourite

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavouriteResponse(
    val id: Int,
    val message: String
): Parcelable
