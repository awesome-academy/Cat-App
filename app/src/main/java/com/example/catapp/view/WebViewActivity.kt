package com.example.catapp.view

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.catapp.databinding.ActivityWebViewBinding
import com.example.catapp.utils.WEB_URL
import com.example.catapp.utils.base.BaseActivity

class WebViewActivity : BaseActivity<ActivityWebViewBinding>(ActivityWebViewBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openWebView()
    }

    private fun openWebView() {
        binding.webViewSignup.run {
            settings.javaScriptEnabled = true
            val wikiUrl = intent.getStringExtra(WEB_URL)
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?, request: WebResourceRequest?
                ): Boolean {
                    return false
                }
            }
            if (wikiUrl != null) {
                loadUrl(wikiUrl)
            }
        }
    }
}
