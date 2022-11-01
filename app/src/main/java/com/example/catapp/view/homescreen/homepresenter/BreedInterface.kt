package com.example.catapp.view.homescreen.homepresenter

import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import java.lang.Exception

interface BreedInterface {
    interface Presenter {
        fun getRemoteBreed()
    }

    interface View {
        fun onGetBreedSuccess(breed: MutableList<BreedItem>)
        fun onError(exception: Exception?)
    }
}
