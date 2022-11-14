package com.example.catapp

import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.data.source.repository.FavRepository
import com.example.catapp.view.homescreen.favpresenter.FavInterface
import com.example.catapp.view.homescreen.favpresenter.FavPresenter
import com.sun.mvp.data.repository.source.remote.OnResultListener
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class FavPresenterTest {
    private val view = mockk<FavInterface.View>(relaxed = true)
    private val repository = mockk<FavRepository>()
    private lateinit var presenter: FavPresenter
    private val listenerFav = slot<OnResultListener<MutableList<FavouriteItem>>>()
    private val favDataSuccess = mutableListOf<FavouriteItem>()
    private val exception = Exception()
    private val testAPI = "live_5gYTnFRh7t9AtwYdCorHJvf2ltsVOEu695wOX6PQjML71C8AqwuLFdiB2BpZghDN"

    @Before
    fun setUp() {
        presenter = FavPresenter(repository, view)
    }

    @Test
    fun getFavSuccess() {
        every {
            repository.getFav(testAPI, capture(listenerFav))
        } answers {
            listenerFav.captured.onSuccess(favDataSuccess)
        }
        presenter.getRemoteFav(testAPI)
        verify {
            view.onGetFavSuccess(favDataSuccess)
        }
    }

    @Test
    fun getFavError() {
        every {
            repository.getFav("", capture(listenerFav))
        } answers {
            listenerFav.captured.onError(exception)
        }
        presenter.getRemoteFav("")
        verify {
            view.onError(exception)
        }
    }
}
