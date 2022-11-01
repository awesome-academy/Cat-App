package com.example.catapp.data.model.responsemodel.breeds

import android.os.Parcelable
import com.example.catapp.data.model.responsemodel.cat.Weight
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BreedItem(
    val adaptability: Int,
    val childFriendly: Int,
    val description: String,
    val dogFriendly: Int,
    val healthIssues: Int,
    val id: String,
    val intelligence: Int,
    val lifeSpan: String,
    val name: String,
    val origin: String,
    val weight: Weight,
    val wikipediaUrl: String,
    val image: Image
): Parcelable
