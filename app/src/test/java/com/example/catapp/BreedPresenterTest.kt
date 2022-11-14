package com.example.catapp

import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.source.repository.BreedRepository
import com.example.catapp.view.homescreen.breedscreenpresenter.BreedInterface
import com.example.catapp.view.homescreen.breedscreenpresenter.BreedPresenter
import com.sun.mvp.data.repository.source.remote.OnResultListener
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class BreedPresenterTest {
    private val view = mockk<BreedInterface.View>(relaxed = true)
    private val repository = mockk<BreedRepository>()
    private lateinit var presenter: BreedPresenter
    private val listenerBreed = slot<OnResultListener<MutableList<BreedItem>>>()
    private val breedsDataSuccess = mutableListOf<BreedItem>()
    private val exception = Exception()

    @Before
    fun setUp() {
        presenter = BreedPresenter(repository, view)
    }

    @Test
    fun getBreedSuccess() {
        every {
            repository.getBreeds(capture(listenerBreed))
        } answers {
            listenerBreed.captured.onSuccess(breedsDataSuccess)
        }
        presenter.getRemoteBreed()
        verify {
            view.onGetBreedSuccess(breedsDataSuccess)
        }
    }

    @Test
    fun getBreedError() {
        every {
            repository.getBreeds(capture(listenerBreed))
        } answers {
            listenerBreed.captured.onError(exception)
        }
        presenter.getRemoteBreed()
        verify {
            view.onError(exception)
        }
    }
}
