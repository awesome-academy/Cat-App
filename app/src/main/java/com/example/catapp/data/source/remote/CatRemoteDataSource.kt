package com.example.catapp.data.source.remote

import com.example.catapp.data.model.bodymodel.SearchModel
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.model.responsemodel.categories.CategoriesItem
import com.example.catapp.data.source.datasource.CatDataSource
import com.sun.mvp.data.repository.source.remote.OnResultListener
import com.example.catapp.data.source.remote.fetchjson.GetJson
import com.example.catapp.utils.BASE_URL_SEARCH
import com.example.catapp.utils.BASE_URL_BREEDS
import com.example.catapp.utils.LIMIT_9
import com.example.catapp.utils.LIMIT_20
import com.example.catapp.utils.BREEDS
import com.example.catapp.utils.BASE_URL_CATEGORIES
import com.example.catapp.utils.CATEGORIES_TAG
import com.example.catapp.utils.IMAGE_TYPE

class CatRemoteDataSource : CatDataSource.Remote {

    override fun getCat(
        userAPI: String, body: SearchModel, listener: OnResultListener<MutableList<Cat>>
    ) {
        val url = BASE_URL_SEARCH + LIMIT_9 +
                "&page=${body.pageNumber}" +
                "&breed_ids=${body.breadID}" +
                "&category_ids=${body.category}" +
                "&order=${body.order}" +
                IMAGE_TYPE
        GetJson(
            urlString = url, keyEntity = "", userAPI = userAPI, listener = listener
        )
    }

    override fun getBreeds(listener: OnResultListener<MutableList<BreedItem>>) {
        GetJson(
            urlString = BASE_URL_BREEDS + LIMIT_20,
            keyEntity = BREEDS,
            userAPI = "",
            listener = listener
        )
    }

    override fun getCategories(listener: OnResultListener<MutableList<CategoriesItem>>) {
        GetJson(
            urlString = BASE_URL_CATEGORIES,
            keyEntity = CATEGORIES_TAG,
            userAPI = "",
            listener = listener
        )
    }

    companion object {
        private var instance: CatRemoteDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: CatRemoteDataSource().also { instance = it }
        }
    }
}
