package com.example.catapp.data.source.remote.fetchjson.delete

import android.os.Handler
import android.os.Looper
import com.example.catapp.data.source.remote.fetchjson.ParseDataWithJson
import com.sun.mvp.data.repository.source.remote.OnResultListener
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DeleteJson<T>(
    private val urlString: String,
    private val keyEntity: String,
    private val userAPI: String,
    private val listener: OnResultListener<T>
) {

    private val mExecutor: Executor = Executors.newSingleThreadExecutor()
    private val mHandler = Handler(Looper.getMainLooper())
    private var data: T? = null

    init {
        deleteAPI()
    }

    private fun deleteAPI() {
        mExecutor.execute {
            val responseJson = deleteJsonFromUrl(urlString)
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

    private fun deleteJsonFromUrl(urlString: String): String {
        val url = URL(urlString)
        val httpURLConnection = url.openConnection() as? HttpURLConnection
        httpURLConnection?.run {
            useCaches = false
            setRequestProperty(X_API_KEY, userAPI)
            connectTimeout = TIME_OUT
            readTimeout = TIME_OUT
            requestMethod = METHOD_DELETE
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
        private const val METHOD_DELETE = "DELETE"
        private const val X_API_KEY = "x-api-key"
    }
}
