package com.example.catapp.data.source.datasource

import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.data.model.responsemodel.favourite.FavouriteResponse
import com.sun.mvp.data.repository.source.remote.OnResultListener

interface SingleFavDataSource {
    interface Remote {
        fun getFav(userAPI: String, listener: OnResultListener<MutableList<FavouriteItem>>)

        fun postFav(
            userAPI: String,
            imgID: String,
            listener: OnResultListener<FavouriteResponse>
        )

        fun delFav(
            userAPI: String,
            delID: String,
            listener: OnResultListener<FavouriteResponse>
        )
    }
}
