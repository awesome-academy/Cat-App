package com.example.catapp.view.loginscreen

import android.content.Intent
import android.os.Bundle
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.utils.base.BaseActivity
import com.example.catapp.databinding.ActivityLoginBinding
import com.example.catapp.utils.PresenterProvider
import com.example.catapp.utils.shortToast
import com.example.catapp.utils.WEB_URL
import com.example.catapp.utils.CAT_URL
import com.example.catapp.utils.USER_API
import com.example.catapp.view.WebViewActivity
import com.example.catapp.view.homescreen.HomeActivity
import java.lang.Exception

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate),
    LoginInterface.View {

    private var loginPresenter: LoginInterface.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        bindingButton()
    }

    private fun setup() {
        loginPresenter = PresenterProvider.loginPresenter(this@LoginActivity)
    }

    override fun onGetCatSuccess(cat: MutableList<Cat>) {
        val userAPI = binding.editTextRequestAPI.text.toString()
        val checker = cat[0].breed
        if (checker == null) {
            shortToast("Your API is invalid!")
        } else {
            startHomeScreen(userAPI)
        }
    }

    private fun bindingButton() {
        binding.btnLogin.setOnClickListener {
            val userAPI = binding.editTextRequestAPI.text.toString()
            if (userAPI != "") {
                loginPresenter?.getRemoteCat(userAPI)
            } else {
                shortToast("Please enter your API")
            }
        }

        binding.textOpenWebView.setOnClickListener {
            val intent = Intent(this@LoginActivity, WebViewActivity::class.java)
            intent.putExtra(WEB_URL, CAT_URL)
            startActivity(intent)
        }
    }

    private fun startHomeScreen(userAPI: String) {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        intent.putExtra(USER_API, userAPI)
        startActivity(intent)
    }

    override fun onError(exception: Exception?) {
        shortToast(exception.toString())
    }
}
