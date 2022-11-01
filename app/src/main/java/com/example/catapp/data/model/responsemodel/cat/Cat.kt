package com.example.catapp.data.model.responsemodel

import android.os.Parcelable
import android.webkit.WebStorage.Origin
import com.example.catapp.data.model.responsemodel.cat.Breed
import com.example.catapp.data.model.responsemodel.categories.CategoriesItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cat(
    var breed: Breed? = null,
    var categories: CategoriesItem? = null,
    var id: String = "",
    var url: String = "",
    var width: Int = 0,
    var height: Int = 0,
) : Parcelable

object CatEntry {
    const val BREEDS = "breeds"
    const val CATEGORIES = "categories"
    const val ID = "id"
    const val URL = "url"
    const val WIDTH = "width"
    const val HEIGHT = "height"
    const val NAME = "name"
    const val ORIGIN = "origin"
    const val ADAPT = "adaptability"
    const val CHILD_FRIENDLY = "child_friendly"
    const val DOG_FRIENDLY = "dog_friendly"
    const val HEALTH = "health_issues"
    const val DES = "description"
    const val INTEL = "intelligence"
    const val LIFE_SPAN = "life_span"
    const val WIKI = "wikipedia_url"
    const val WEIGHT = "weight"
    const val METRIC = "metric"
    const val IMAGE = "image"
}
