package com.example.catapp.view.homescreen.homepresenter

import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.source.repository.BreedRepository
import com.sun.mvp.data.repository.source.remote.OnResultListener
import java.lang.Exception

class BreedPresenter(
    private val breedRepository: BreedRepository,
    private val view: BreedInterface.View
) : BreedInterface.Presenter {

    override fun getRemoteBreed() {
        breedRepository.getBreeds(object : OnResultListener<MutableList<BreedItem>> {
            override fun onSuccess(data: MutableList<BreedItem>) {
                view.onGetBreedSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view.onError(exception)
            }

        })
    }
}
