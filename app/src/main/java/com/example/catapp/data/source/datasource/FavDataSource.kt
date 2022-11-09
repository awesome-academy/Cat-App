package com.example.catapp.data.source.datasource

import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.sun.mvp.data.repository.source.remote.OnResultListener

interface FavDataSource {
    interface Remote {
        fun getFav(userAPI: String, listener: OnResultListener<MutableList<FavouriteItem>>)
    }
}
