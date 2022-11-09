package com.example.catapp.view.homescreen.favpresenter.singlefavpresenter

import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.data.model.responsemodel.favourite.FavouriteResponse
import com.example.catapp.data.source.repository.SingleFavRepository
import com.sun.mvp.data.repository.source.remote.OnResultListener
import java.lang.Exception

class SingleFavPresenter(
    private val favRepository: SingleFavRepository, private val view: SingleFavInterface.View
) : SingleFavInterface.Presenter {

    override fun getRemoteFav(userAPI: String) {
        favRepository.getFav(userAPI, object : OnResultListener<MutableList<FavouriteItem>>{
            override fun onSuccess(data: MutableList<FavouriteItem>) {
                view.onGetFavSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view.onError(exception)
            }
        })
    }

    override fun postRemoteFav(userAPI: String, imgID: String) {
        favRepository.postFav(userAPI, imgID, object : OnResultListener<FavouriteResponse>{
            override fun onSuccess(data: FavouriteResponse) {
                view.onPostFavSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view.onError(exception)
            }
        })
    }

    override fun deleteRemoteFav(userAPI: String, delID: String) {
        favRepository.delFav(userAPI, delID, object : OnResultListener<FavouriteResponse>{
            override fun onSuccess(data: FavouriteResponse) {
                view.onDeleteFavSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view.onError(exception)
            }
        })
    }
}
