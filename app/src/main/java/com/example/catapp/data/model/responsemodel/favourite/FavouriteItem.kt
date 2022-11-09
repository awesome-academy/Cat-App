package com.example.catapp.data.model.responsemodel.favourite

import com.example.catapp.data.model.responsemodel.breeds.Image

data class FavouriteItem(
    val id: Int,
    val image: Image,
    val user_id: String
)