package com.example.catapp

import com.example.catapp.data.model.bodymodel.SearchModel
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.model.responsemodel.categories.CategoriesItem
import com.example.catapp.data.source.repository.CatRepository
import com.example.catapp.view.homescreen.imagescreenpresenter.CatInterface
import com.example.catapp.view.homescreen.imagescreenpresenter.CatPresenter
import com.sun.mvp.data.repository.source.remote.OnResultListener
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ImagePresenterTest {
    private val view = mockk<CatInterface.View>(relaxed = true)
    private val repository = mockk<CatRepository>()
    private lateinit var presenter: CatPresenter
    private val listenerCat = slot<OnResultListener<MutableList<Cat>>>()
    private val listenerBreed = slot<OnResultListener<MutableList<BreedItem>>>()
    private val listenerCategory = slot<OnResultListener<MutableList<CategoriesItem>>>()
    private val catDataSuccess = mutableListOf<Cat>()
    private val breedDataSuccess = mutableListOf<BreedItem>()
    private val categoryDataSuccess = mutableListOf<CategoriesItem>()
    private val exception = Exception()
    private val testAPI = "live_5gYTnFRh7t9AtwYdCorHJvf2ltsVOEu695wOX6PQjML71C8AqwuLFdiB2BpZghDN"
    private var searchFilter = SearchModel()

    @Before
    fun setUp() {
        presenter = CatPresenter(repository, view)
    }

    @Test
    fun getCatSuccess() {
        every {
            repository.getCat(testAPI, searchFilter, capture(listenerCat))
        } answers {
            listenerCat.captured.onSuccess(catDataSuccess)
        }
        presenter.getRemoteCat(testAPI, searchFilter)
        verify {
            view.onGetCatSuccess(catDataSuccess)
        }
    }

    @Test
    fun getCatError() {
        every {
            repository.getCat("", searchFilter, capture(listenerCat))
        } answers {
            listenerBreed.captured.onError(exception)
        }
        presenter.getRemoteCat("", searchFilter)
        verify {
            view.onError(exception)
        }
    }

    @Test
    fun getBreedSuccess() {
        every {
            repository.getBreeds(capture(listenerBreed))
        } answers {
            listenerBreed.captured.onSuccess(breedDataSuccess)
        }
        presenter.getRemoteBreed()
        verify {
            view.onGetBreedSuccess(breedDataSuccess)
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

    @Test
    fun getCategorySuccess() {
        every {
            repository.getCategories(capture(listenerCategory))
        } answers {
            listenerCategory.captured.onSuccess(categoryDataSuccess)
        }
        presenter.getRemoteCategory()
        verify {
            view.onGetBreedSuccess(breedDataSuccess)
        }
    }

    @Test
    fun getCategoryError() {
        every {
            repository.getCategories(capture(listenerCategory))
        } answers {
            listenerCategory.captured.onError(exception)
        }
        presenter.getRemoteCategory()
        verify {
            view.onError(exception)
        }
    }
}
