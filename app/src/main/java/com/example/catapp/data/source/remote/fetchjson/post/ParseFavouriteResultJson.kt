package com.example.catapp.data.source.remote.fetchjson.post

import com.example.catapp.data.model.responsemodel.favourite.FavouriteResponse
import org.json.JSONObject

class ParseFavouriteResultJson {

    fun favResultParse(jsonObject: JSONObject): FavouriteResponse {
        val message = jsonObject.optString(responseMessage)
        val id = jsonObject.optInt(responseID)

        return FavouriteResponse(id, message)
    }

    companion object {
        const val responseMessage = "message"
        const val responseID = "id"
    }
}
