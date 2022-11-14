package com.example.catapp

import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.data.model.responsemodel.favourite.FavouriteResponse
import com.example.catapp.data.source.repository.SingleFavRepository
import com.example.catapp.view.homescreen.favpresenter.singlefavpresenter.SingleFavInterface
import com.example.catapp.view.homescreen.favpresenter.singlefavpresenter.SingleFavPresenter
import com.sun.mvp.data.repository.source.remote.OnResultListener
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class FavDetailPresenterTest {
    private val view = mockk<SingleFavInterface.View>(relaxed = true)
    private val repository = mockk<SingleFavRepository>()
    private lateinit var presenter: SingleFavPresenter
    private val listenerFav = slot<OnResultListener<MutableList<FavouriteItem>>>()
    private val listenerFavResponse = slot<OnResultListener<FavouriteResponse>>()
    private val favDataSuccess = mutableListOf<FavouriteItem>()
    private val favResponseDataSuccess = FavouriteResponse(100098999, "SUCCESS")
    private val exception = Exception()
    private val testAPI = "live_5gYTnFRh7t9AtwYdCorHJvf2ltsVOEu695wOX6PQjML71C8AqwuLFdiB2BpZghDN"
    private val testImgID = "MTkxNjA2NA"

    @Before
    fun setUp() {
        presenter = SingleFavPresenter(repository, view)
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

    @Test
    fun postFavSuccess() {
        every {
            repository.postFav(testAPI, testImgID,capture(listenerFavResponse))
        } answers {
            listenerFavResponse.captured.onSuccess(favResponseDataSuccess)
        }
        presenter.postRemoteFav(testAPI, testImgID)
        verify {
            view.onPostFavSuccess(favResponseDataSuccess)
        }
    }

    @Test
    fun postFavError() {
        every {
            repository.postFav("","", capture(listenerFavResponse))
        } answers {
            listenerFavResponse.captured.onError(exception)
        }
        presenter.postRemoteFav("", "")
        verify {
            view.onError(exception)
        }
    }

    @Test
    fun delFavSuccess() {
        every {
            repository.delFav(testAPI, testImgID,capture(listenerFavResponse))
        } answers {
            listenerFavResponse.captured.onSuccess(favResponseDataSuccess)
        }
        presenter.deleteRemoteFav(testAPI, testImgID)
        verify {
            view.onDeleteFavSuccess(favResponseDataSuccess)
        }
    }

    @Test
    fun delFavError() {
        every {
            repository.delFav("","", capture(listenerFavResponse))
        } answers {
            listenerFavResponse.captured.onError(exception)
        }
        presenter.deleteRemoteFav("", "")
        verify {
            view.onError(exception)
        }
    }
}
