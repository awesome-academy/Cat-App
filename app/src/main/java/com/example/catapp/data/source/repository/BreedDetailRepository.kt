package com.example.catapp.data.source.repository

import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.source.datasource.BreedsDetailDataSource
import com.sun.mvp.data.repository.source.remote.OnResultListener

class BreedDetailRepository(private val remote: BreedsDetailDataSource.Remote):  BreedsDetailDataSource.Remote {

    override fun getBreeds(id: String, listener: OnResultListener<BreedItem>) {
        remote.getBreeds(id, listener)
    }

    companion object {
        private var instance: BreedDetailRepository? = null

        fun getInstance(remote: BreedsDetailDataSource.Remote) =
            synchronized(this) {
                instance ?: BreedDetailRepository(remote).also { instance = it }
            }
    }
}
