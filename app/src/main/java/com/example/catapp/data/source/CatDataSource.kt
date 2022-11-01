package com.example.catapp.data.source

import com.example.catapp.data.model.responsemodel.Cat
import com.sun.mvp.data.repository.source.remote.OnResultListener

interface CatDataSource {

    interface Local {
        fun getCatLocal(listener: OnResultListener<MutableList<Cat>>)
    }

    interface Remote {
        fun getCat(userAPI: String, limit: String, listener: OnResultListener<MutableList<Cat>>)
    }
}
