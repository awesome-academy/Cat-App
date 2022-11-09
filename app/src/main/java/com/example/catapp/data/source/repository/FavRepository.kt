package com.example.catapp.data.source.repository

import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.data.source.datasource.FavDataSource
import com.sun.mvp.data.repository.source.remote.OnResultListener

class FavRepository(
    private val remoteFav: FavDataSource.Remote,
) : FavDataSource.Remote {

    override fun getFav(userAPI: String, listener: OnResultListener<MutableList<FavouriteItem>>) {
        remoteFav.getFav(userAPI, listener)
    }

    companion object {
        private var instance: FavRepository? = null

        fun getInstance(remote: FavDataSource.Remote) =
            synchronized(this) {
                instance ?: FavRepository(remote).also { instance = it }
            }
    }
}
