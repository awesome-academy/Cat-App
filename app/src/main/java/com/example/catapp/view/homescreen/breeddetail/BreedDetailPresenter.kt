package com.example.catapp.view.homescreen.breeddetail

import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.source.repository.BreedDetailRepository
import com.sun.mvp.data.repository.source.remote.OnResultListener
import java.lang.Exception

class BreedDetailPresenter(
    private val breedRepository: BreedDetailRepository,
    private val view: BreedDetailInterface.View
) : BreedDetailInterface.Presenter {

    override fun getRemoteBreed(id: String) {
        breedRepository.getBreeds(id, object : OnResultListener<BreedItem> {
            override fun onSuccess(data: BreedItem) {
                view.onGetBreedSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view.onError(exception)
            }
        })
    }
}
