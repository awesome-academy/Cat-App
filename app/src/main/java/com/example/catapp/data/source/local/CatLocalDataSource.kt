package com.example.catapp.data.source.local

import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.source.datasource.CatDataSource
import com.sun.mvp.data.repository.source.remote.OnResultListener

class CatLocalDataSource: CatDataSource.Local {

    override fun getCatLocal(listener: OnResultListener<MutableList<Cat>>) {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: CatLocalDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: CatLocalDataSource().also { instance = it }
        }
    }
}
