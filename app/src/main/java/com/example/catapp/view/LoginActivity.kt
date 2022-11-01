package com.example.catapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.source.repository.CatRepository
import com.example.catapp.utils.base.BaseActivity
import com.example.catapp.view.presenter.CatInterface
import com.example.catapp.view.presenter.CatPresenter
import com.sun.mvp.data.repository.source.local.CatLocalDataSource
import com.example.catapp.data.source.remote.CatRemoteDataSource
import com.example.catapp.databinding.ActivityLoginBinding
import com.example.catapp.utils.LIMIT_1
import com.example.catapp.utils.USER_API
import com.example.catapp.utils.shortToast
import com.example.catapp.view.homescreen.HomeActivity
import java.lang.Exception

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate),
    CatInterface.View {

    private var catPresenter: CatInterface.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        bindingButton()
    }

    private fun setup() {
        catPresenter = CatPresenter(
            CatRepository.getInstance(
                CatRemoteDataSource.getInstance(),
                CatLocalDataSource.getInstance()
            ), this@LoginActivity
        )
    }

    override fun onGetCatSuccess(cat: MutableList<Cat>) {
        val userAPI = binding.editTextRequestAPI.text.toString()
        val checker = cat[0].breed
        if (checker == null) {
            startHomeScreen(userAPI)
            shortToast("Your API is invalid!")
        } else {
            startHomeScreen(userAPI)
        }
    }

    private fun bindingButton() {
        binding.buttonLogin.setOnClickListener {
            val userAPI = binding.editTextRequestAPI.text.toString()
            if (userAPI != "") {
                catPresenter?.getRemoteCat(userAPI, LIMIT_1)
            } else {
                shortToast("Please enter your API")
            }
        }

        binding.textOpenWebView.setOnClickListener {
            val intent = Intent(this@LoginActivity, WebViewActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startHomeScreen(userAPI: String){
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        intent.putExtra(USER_API, userAPI)
        startActivity(intent)
    }

    override fun onError(exception: Exception?) {
        Log.d("Lmeow", "exception: $exception")
    }
}
