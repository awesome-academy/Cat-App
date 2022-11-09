package com.example.catapp.utils

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Looper
import java.io.File
import java.util.concurrent.Executor
import java.util.concurrent.Executors

fun Context.downloadImg(url: String) {
    val mExecutor: Executor = Executors.newSingleThreadExecutor()
    val mHandler = Handler(Looper.getMainLooper())
    val directory = File(Environment.DIRECTORY_PICTURES)
    if (!directory.exists()) {
        directory.mkdirs()
    }
    val downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val downloadUri = Uri.parse(url)
    val imgTitle = url.substring(url.lastIndexOf("/") + 1)
    val request = DownloadManager.Request(downloadUri).run {
        setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI).setAllowedOverRoaming(
            false
        ).setTitle(imgTitle).setDescription("")
            .setDestinationInExternalPublicDir(directory.toString(), imgTitle)
    }
    val downloadID = downloadManager.enqueue(request)
    val query = DownloadManager.Query().setFilterById(downloadID)
    mExecutor.execute {
        var downloading = true
        while (downloading) {
            val cursor: Cursor = downloadManager.query(query)
            cursor.moveToFirst()
            val status = cursor.getInt(
                cursor.getColumnIndex(DownloadManager.COLUMN_STATUS).toInt()
            )
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                downloading = false
                mHandler.post {
                    shortToast("Downloaded ($imgTitle) Successfully")
                }
            }
            cursor.close()
        }
    }
}
