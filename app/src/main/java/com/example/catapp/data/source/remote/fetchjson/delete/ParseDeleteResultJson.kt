package com.example.catapp.data.source.remote.fetchjson.delete

import com.example.catapp.data.model.responsemodel.favourite.FavouriteResponse
import com.example.catapp.utils.NONE
import org.json.JSONObject

class ParseDeleteResultJson {

    fun delResultParse(jsonObject: JSONObject): FavouriteResponse {
        val message = jsonObject.optString(responseMessage)

        return FavouriteResponse(NONE,message)
    }

    companion object {
        const val responseMessage = "message"
    }
}
