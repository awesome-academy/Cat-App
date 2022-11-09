package com.example.catapp.data.source.remote.fetchjson.post

import org.json.JSONObject

class ConvertFavouriteJson {

    fun convertToJson(imgID: String): String {
        val jsonObject = JSONObject().run {
            put("image_id", imgID)
        }
        return jsonObject.toString()
    }
}
