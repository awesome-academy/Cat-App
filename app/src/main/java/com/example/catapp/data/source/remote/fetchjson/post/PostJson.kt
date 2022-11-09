package com.example.catapp.data.source.remote.fetchjson.post

import android.os.Handler
import android.os.Looper
import com.example.catapp.data.source.remote.fetchjson.ParseDataWithJson
import com.sun.mvp.data.repository.source.remote.OnResultListener
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class PostJson<T>(
    private val urlString: String,
    private val keyEntity: String,
    private val userAPI: String,
    private val imgID: String,
    private val listener: OnResultListener<T>
) {

    private val mExecutor: Executor = Executors.newSingleThreadExecutor()
    private val mHandler = Handler(Looper.getMainLooper())
    private var data: T? = null

    init {
        postAPI()
    }

    private fun postAPI() {
        mExecutor.execute {
            val responseJson = postJsonFromUrl(urlString)
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

    private fun postJsonFromUrl(urlString: String): String {
        val url = URL(urlString)
        val httpURLConnection = url.openConnection() as? HttpURLConnection
        httpURLConnection?.run {
            useCaches = false
            requestMethod = METHOD_POST
            setRequestProperty(X_API_KEY, userAPI)
            setRequestProperty(CONTENT_TYPE_HEAD, CONTENT_TYPE)
            doOutput = true
            doInput= true
            connect()
            val os = OutputStreamWriter(outputStream)
            val body = ConvertFavouriteJson().convertToJson(imgID)
            os.run {
                write(body)
                flush()
                close()
            }
            connectTimeout = TIME_OUT
            readTimeout = TIME_OUT
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
        private const val METHOD_POST = "POST"
        private const val X_API_KEY = "x-api-key"
        private const val CONTENT_TYPE_HEAD = "Content-Type"
        private const val CONTENT_TYPE = "application/json"
    }
}
