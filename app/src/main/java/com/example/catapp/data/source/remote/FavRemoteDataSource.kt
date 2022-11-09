package com.example.catapp.data.source.remote

import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.data.source.datasource.FavDataSource
import com.example.catapp.data.source.remote.fetchjson.GetJson
import com.example.catapp.utils.FAVOURITES
import com.example.catapp.utils.BASE_URL_FAVOURITE
import com.sun.mvp.data.repository.source.remote.OnResultListener

class FavRemoteDataSource : FavDataSource.Remote {

    override fun getFav(userAPI: String, listener: OnResultListener<MutableList<FavouriteItem>>) {
        GetJson(
            urlString = BASE_URL_FAVOURITE,
            keyEntity = FAVOURITES,
            userAPI = userAPI,
            listener = listener
        )
    }

    companion object {
        private var instance: FavRemoteDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: FavRemoteDataSource().also { instance = it }
        }
    }
}
