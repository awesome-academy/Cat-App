package com.example.catapp.data.source.repository

import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.source.CatDataSource
import com.sun.mvp.data.repository.source.remote.OnResultListener

class CatRepository(
    private val remote: CatDataSource.Remote,
    private val local: CatDataSource.Local
) : CatDataSource.Local, CatDataSource.Remote {
    override fun getCatLocal(listener: OnResultListener<MutableList<Cat>>) {
        TODO("Not yet implemented")
    }

    override fun getCat(
        userAPI: String,
        limit: String,
        listener: OnResultListener<MutableList<Cat>>
    ) {
        remote.getCat(userAPI, limit, listener)
    }

    companion object {
        private var instance: CatRepository? = null

        fun getInstance(remote: CatDataSource.Remote, local: CatDataSource.Local) =
            synchronized(this) {
                instance ?: CatRepository(remote, local).also { instance = it }
            }
    }
}
