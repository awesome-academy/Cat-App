package com.example.catapp.data.source.repository

import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.source.BreedsDataSource
import com.sun.mvp.data.repository.source.remote.OnResultListener

class BreedRepository(private val remote: BreedsDataSource.Remote):  BreedsDataSource.Remote {

    override fun getBreeds(listener: OnResultListener<MutableList<BreedItem>>) {
        remote.getBreeds(listener)
    }

    companion object {
        private var instance: BreedRepository? = null

        fun getInstance(remote: BreedsDataSource.Remote) =
            synchronized(this) {
                instance ?: BreedRepository(remote).also { instance = it }
            }
    }
}
