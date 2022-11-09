package com.example.catapp.data.source.remote

import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.source.datasource.BreedsDetailDataSource
import com.example.catapp.data.source.remote.fetchjson.GetJson
import com.example.catapp.utils.BASE_URL_BREEDS_SEARCH
import com.example.catapp.utils.BREEDS_SEARCH
import com.sun.mvp.data.repository.source.remote.OnResultListener

class BreedsDetailRemoteDataSource : BreedsDetailDataSource.Remote {

    override fun getBreeds(id: String, listener: OnResultListener<BreedItem>) {
        GetJson(
            urlString = BASE_URL_BREEDS_SEARCH + id,
            keyEntity = BREEDS_SEARCH,
            userAPI = "",
            listener = listener
        )
    }

    companion object {
        private var instance: BreedsDetailRemoteDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: BreedsDetailRemoteDataSource().also { instance = it }
        }
    }
}
