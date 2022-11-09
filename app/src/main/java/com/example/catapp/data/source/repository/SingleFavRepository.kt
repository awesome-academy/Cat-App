package com.example.catapp.data.source.repository

import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.data.model.responsemodel.favourite.FavouriteResponse
import com.example.catapp.data.source.datasource.SingleFavDataSource
import com.sun.mvp.data.repository.source.remote.OnResultListener

class SingleFavRepository(
    private val remoteFav: SingleFavDataSource.Remote,
) : SingleFavDataSource.Remote {

    override fun getFav(userAPI: String, listener: OnResultListener<MutableList<FavouriteItem>>) {
        remoteFav.getFav(userAPI, listener)
    }

    override fun postFav(
        userAPI: String,
        imgID: String,
        listener: OnResultListener<FavouriteResponse>
    ) {
        remoteFav.postFav(userAPI, imgID, listener)
    }

    override fun delFav(
        userAPI: String,
        delID: String,
        listener: OnResultListener<FavouriteResponse>
    ) {
        remoteFav.delFav(userAPI, delID, listener)
    }

    companion object {
        private var instance: SingleFavRepository? = null

        fun getInstance(remote: SingleFavDataSource.Remote) =
            synchronized(this) {
                instance ?: SingleFavRepository(remote).also { instance = it }
            }
    }
}
