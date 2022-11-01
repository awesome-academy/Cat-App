package com.example.catapp.data.source.remote

import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.utils.BASE_URL_SEARCH
import com.example.catapp.utils.BREED_ID
import com.example.catapp.data.source.CatDataSource
import com.sun.mvp.data.repository.source.remote.OnResultListener
import com.example.catapp.data.source.remote.fetchjson.GetJsonFromUrl

class CatRemoteDataSource : CatDataSource.Remote {

    override fun getCat(userAPI: String, limit: String, listener: OnResultListener<MutableList<Cat>>) {
        GetJsonFromUrl(
            urlString = BASE_URL_SEARCH + limit + BREED_ID,
            keyEntity = "",
            userAPI = userAPI,
            listener = listener
        )
    }


    companion object {
        private var instance: CatRemoteDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: CatRemoteDataSource().also { instance = it }
        }
    }
}
