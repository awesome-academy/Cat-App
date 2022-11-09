package com.example.catapp.view.homescreen.favpresenter

import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import java.lang.Exception

interface FavInterface {
    interface Presenter {
        fun getRemoteFav(userAPI: String)
    }

    interface View {
        fun onGetFavSuccess(favList: MutableList<FavouriteItem>)
        fun onError(exception: Exception?)
    }
}
