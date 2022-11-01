package com.example.catapp.data.source.remote.fetchjson

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.sun.mvp.data.repository.source.remote.OnResultListener
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GetJsonFromUrl<T> (
    private val urlString: String,
    private val keyEntity: String,
    private val userAPI: String,
    private val listener: OnResultListener<T>
) {

    private val mExecutor: Executor = Executors.newSingleThreadExecutor()
    private val mHandler = Handler(Looper.getMainLooper())
    private var exception: Exception? = null
    private var data: T? = null

    init {
        callAPI()
    }

    private fun callAPI() {
        mExecutor.execute {
            val responseJson = getJsonFromUrl(urlString)
            data = ParseDataWithJson().parseJsonToData(JSONArray(responseJson), keyEntity) as? T
            mHandler.post {
                try {
                    data?.let { listener.onSuccess(it)
                    }

                } catch (e: Exception) {
                    listener.onError(exception)
                }
            }
        }
    }

    private fun getJsonFromUrl(urlString: String): String {
        val url = URL(urlString)
        val httpURLConnection = url.openConnection() as? HttpURLConnection
        httpURLConnection?.run {
            useCaches = false
            setRequestProperty(X_API_KEY, userAPI)
            connectTimeout = TIME_OUT
            readTimeout = TIME_OUT
            requestMethod = METHOD_GET
            doInput = true
            connect()
        }
        val bufferedReader = BufferedReader(InputStreamReader(httpURLConnection?.inputStream))
        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        bufferedReader.close()
        httpURLConnection?.disconnect()
        return stringBuilder.toString()
    }

    companion object {
        private const val TIME_OUT = 15000
        private const val METHOD_GET = "GET"
        private const val METHOD_POST = "POST"
        private const val X_API_KEY = "x-api-key"
    }
}
