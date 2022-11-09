package com.example.catapp.data.model.bodymodel

import com.example.catapp.utils.ORDER_RANDOM

data class SearchModel(
    val pageNumber: Int = 0,
    val order: String = ORDER_RANDOM,
    val breadID: String = "",
    val category: String = ""
)
