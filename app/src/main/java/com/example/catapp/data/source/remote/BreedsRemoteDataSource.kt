package com.example.catapp.data.source.remote

import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.source.datasource.BreedsDataSource
import com.example.catapp.data.source.remote.fetchjson.GetJson
import com.example.catapp.utils.BASE_URL_BREEDS
import com.example.catapp.utils.BREEDS
import com.example.catapp.utils.LIMIT_20
import com.sun.mvp.data.repository.source.remote.OnResultListener

class BreedsRemoteDataSource : BreedsDataSource.Remote {

    override fun getBreeds(listener: OnResultListener<MutableList<BreedItem>>) {
        GetJson(
            urlString = BASE_URL_BREEDS + LIMIT_20,
            keyEntity = BREEDS,
            userAPI = "",
            listener = listener
        )
    }

    companion object {
        private var instance: BreedsRemoteDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: BreedsRemoteDataSource().also { instance = it }
        }
    }
}
