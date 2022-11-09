package com.example.catapp.data.source.datasource

import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.sun.mvp.data.repository.source.remote.OnResultListener

interface BreedsDetailDataSource {
    interface Remote {
        fun getBreeds(id: String, listener: OnResultListener<BreedItem>)
    }
}
