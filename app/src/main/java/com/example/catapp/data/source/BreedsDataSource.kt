package com.example.catapp.data.source

import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.sun.mvp.data.repository.source.remote.OnResultListener

interface BreedsDataSource {
    interface Remote {
        fun getBreeds(listener: OnResultListener<MutableList<BreedItem>>)
    }
}
