package com.example.catapp.view.homescreen.favpresenter

import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.data.source.repository.FavRepository
import com.sun.mvp.data.repository.source.remote.OnResultListener
import java.lang.Exception

class FavPresenter(
    private val favRepository: FavRepository, private val view: FavInterface.View
) : FavInterface.Presenter {

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
}
