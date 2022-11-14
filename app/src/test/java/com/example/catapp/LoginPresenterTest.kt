package com.example.catapp

import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.source.repository.LoginRepository
import com.example.catapp.view.loginscreen.LoginInterface
import com.example.catapp.view.loginscreen.LoginPresenter
import com.sun.mvp.data.repository.source.remote.OnResultListener
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LoginPresenterTest {
    private val view = mockk<LoginInterface.View>(relaxed = true)
    private val repository = mockk<LoginRepository>()
    private lateinit var presenter: LoginPresenter
    private val listenerCat = slot<OnResultListener<MutableList<Cat>>>()
    private val catDataSuccess = mutableListOf<Cat>()
    private val exception = Exception()
    private val testAPI = "live_5gYTnFRh7t9AtwYdCorHJvf2ltsVOEu695wOX6PQjML71C8AqwuLFdiB2BpZghDN"

    @Before
    fun setUp() {
        presenter = LoginPresenter(repository, view)
    }

    @Test
    fun getCatSuccess() {
        every {
            repository.getCat(testAPI, capture(listenerCat))
        } answers {
            listenerCat.captured.onSuccess(catDataSuccess)
        }
        presenter.getRemoteCat(testAPI)
        verify {
            view.onGetCatSuccess(catDataSuccess)
        }
    }

    @Test
    fun getCatError() {
        every {
            repository.getCat("", capture(listenerCat))
        } answers {
            listenerCat.captured.onError(exception)
        }
        presenter.getRemoteCat("")
        verify {
            view.onError(exception)
        }
    }
}
