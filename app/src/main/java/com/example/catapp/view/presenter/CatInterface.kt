package com.example.catapp.view.presenter

import com.example.catapp.data.model.responsemodel.Cat
import java.lang.Exception

interface CatInterface {
    interface Presenter {
        fun getLocalCat()
        fun getRemoteCat(userAPI: String, limit: String)
    }

    interface View {
        fun onGetCatSuccess(cat: MutableList<Cat>)
        fun onError(exception: Exception?)
    }
}
