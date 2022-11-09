package com.example.catapp.view.homescreen.breeddetail

import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import java.lang.Exception

interface BreedDetailInterface {

    interface Presenter {
        fun getRemoteBreed(id: String)
    }

    interface View {
        fun onGetBreedSuccess(breed: BreedItem)
        fun onError(exception: Exception?)
    }
}
