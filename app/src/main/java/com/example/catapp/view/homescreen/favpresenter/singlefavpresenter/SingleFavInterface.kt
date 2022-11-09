package com.example.catapp.view.homescreen.favpresenter.singlefavpresenter

import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.data.model.responsemodel.favourite.FavouriteResponse
import java.lang.Exception

interface SingleFavInterface {
    interface Presenter {
        fun getRemoteFav(userAPI: String)
        fun postRemoteFav(userAPI: String, imgID: String)
        fun deleteRemoteFav(userAPI: String, delID: String)
    }

    interface View {
        fun onGetFavSuccess(favList: MutableList<FavouriteItem>)
        fun onPostFavSuccess(favResponse: FavouriteResponse)
        fun onDeleteFavSuccess(favResponse: FavouriteResponse)
        fun onError(exception: Exception?)
    }
}
