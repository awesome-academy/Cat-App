package com.example.catapp

import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.model.responsemodel.breeds.Image
import com.example.catapp.data.model.responsemodel.cat.Weight
import com.example.catapp.data.source.repository.BreedDetailRepository
import com.example.catapp.view.homescreen.breeddetail.BreedDetailInterface
import com.example.catapp.view.homescreen.breeddetail.BreedDetailPresenter
import com.sun.mvp.data.repository.source.remote.OnResultListener
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class BreedDetailPresenterTest {
    private val view = mockk<BreedDetailInterface.View>(relaxed = true)
    private val repository = mockk<BreedDetailRepository>()
    private lateinit var presenter: BreedDetailPresenter
    private val listenerBreed = slot<OnResultListener<BreedItem>>()
    private val weight = Weight("")
    private val image = Image("", "")
    private val breedDataSuccess = BreedItem(
        5, 4, "",
        5, 4, "",
        4, "", "",
        "", weight, "", image
    )
    private val exception = Exception()
    private val breedID = "abys"

    @Before
    fun setUp() {
        presenter = BreedDetailPresenter(repository, view)
    }

    @Test
    fun getBreedSuccess() {
        every {
            repository.getBreeds(breedID, capture(listenerBreed))
        } answers {
            listenerBreed.captured.onSuccess(breedDataSuccess)
        }
        presenter.getRemoteBreed(breedID)
        verify {
            view.onGetBreedSuccess(breedDataSuccess)
        }
    }

    @Test
    fun getBreedError() {
        every {
            repository.getBreeds("", capture(listenerBreed))
        } answers {
            listenerBreed.captured.onError(exception)
        }
        presenter.getRemoteBreed("")
        verify {
            view.onError(exception)
        }
    }
}
