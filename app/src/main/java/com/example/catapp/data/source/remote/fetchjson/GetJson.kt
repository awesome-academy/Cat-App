package com.example.catapp.data.source.remote.fetchjson

import android.os.Handler
import android.os.Looper
import com.example.catapp.utils.BREEDS_SEARCH
import com.sun.mvp.data.repository.source.remote.OnResultListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GetJson<T>(
    private val urlString: String,
    private val keyEntity: String,
    private val userAPI: String,
    private val listener: OnResultListener<T>
) {

    private val mExecutor: Executor = Executors.newSingleThreadExecutor()
    private val mHandler = Handler(Looper.getMainLooper())
    private var data: T? = null

    init {
        when (keyEntity){
            BREEDS_SEARCH -> callAPISearch()
            else ->  callAPI()
        }
    }

    private fun callAPI() {
        mExecutor.execute {
            val responseJson = getJsonFromUrl(urlString)
            data = ParseDataWithJson().parseJsonToData(JSONArray(responseJson), keyEntity) as? T
            mHandler.post {
                try {
                    data?.let { listener.onSuccess(it) }
                } catch (e: JSONException) {
                    listener.onError(e)
                }
            }
        }
    }

    private fun callAPISearch(){
        mExecutor.execute {
            val responseJson = getJsonFromUrl(urlString)
            data = ParseDataWithJson().parseJsonToObject(JSONObject(responseJson), keyEntity) as? T
            mHandler.post {
                try {
                    data?.let { listener.onSuccess(it) }
                } catch (e: JSONException) {
                    listener.onError(e)
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
            setRequestProperty(CONTENT_TYPE_HEAD, CONTENT_TYPE)
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
        private const val X_API_KEY = "x-api-key"
        private const val CONTENT_TYPE_HEAD = "Content-Type"
        private const val CONTENT_TYPE = "application/json"
    }
}
