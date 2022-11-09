package com.example.catapp.data.source.remote

import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.data.model.responsemodel.favourite.FavouriteResponse
import com.example.catapp.data.source.datasource.SingleFavDataSource
import com.example.catapp.data.source.remote.fetchjson.GetJson
import com.example.catapp.data.source.remote.fetchjson.delete.DeleteJson
import com.example.catapp.data.source.remote.fetchjson.post.PostJson
import com.example.catapp.utils.FAVOURITES
import com.example.catapp.utils.BASE_URL_FAVOURITE
import com.example.catapp.utils.DELETE_FAV
import com.example.catapp.utils.POST_FAV_TAG
import com.sun.mvp.data.repository.source.remote.OnResultListener

class SingleFavRemoteDataSource : SingleFavDataSource.Remote {

    override fun getFav(userAPI: String, listener: OnResultListener<MutableList<FavouriteItem>>) {
        GetJson(
            urlString = BASE_URL_FAVOURITE,
            keyEntity = FAVOURITES,
            userAPI = userAPI,
            listener = listener
        )
    }

    override fun postFav(
        userAPI: String,
        imgID: String,
        listener: OnResultListener<FavouriteResponse>
    ) {
        PostJson(
            urlString = BASE_URL_FAVOURITE,
            keyEntity = POST_FAV_TAG,
            userAPI = userAPI,
            imgID = imgID,
            listener = listener
        )
    }

    override fun delFav(
        userAPI: String,
        delID: String,
        listener: OnResultListener<FavouriteResponse>
    ) {
        DeleteJson(
            urlString = "$BASE_URL_FAVOURITE/$delID",
            keyEntity = DELETE_FAV,
            userAPI = userAPI,
            listener = listener
        )
    }

    companion object {
        private var instance: SingleFavRemoteDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: SingleFavRemoteDataSource().also { instance = it }
        }
    }
}
