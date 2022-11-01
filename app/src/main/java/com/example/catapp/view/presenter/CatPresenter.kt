package com.example.catapp.view.presenter

import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.source.repository.CatRepository
import com.sun.mvp.data.repository.source.remote.OnResultListener
import java.lang.Exception

class CatPresenter(
    private val catRepository: CatRepository,
    private val view: CatInterface.View
) : CatInterface.Presenter {

    override fun getLocalCat() {
        TODO("Not yet implemented")
    }

    override fun getRemoteCat(userAPI: String, limit: String) {
        catRepository.getCat(userAPI, limit, object : OnResultListener<MutableList<Cat>> {
            override fun onSuccess(data: MutableList<Cat>) {
                view.onGetCatSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view.onError(exception)
            }

        })
    }
}
