package com.example.catapp.data.source.remote.fetchjson

import com.example.catapp.data.model.responsemodel.CatEntry
import com.example.catapp.data.model.responsemodel.breeds.Image
import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import org.json.JSONObject

class ParseFavouriteJson {

    companion object {
        const val USER_ID = "user_id"
    }

    fun breedParse(jsonObject: JSONObject): FavouriteItem {
        val favID = jsonObject.optInt(CatEntry.ID)
        val userID = jsonObject.optString(USER_ID)

        val imageUrl = jsonObject.getJSONObject(CatEntry.IMAGE).optString(CatEntry.URL)
        val imageID = jsonObject.getJSONObject(CatEntry.IMAGE).optString(CatEntry.ID)
        val image = Image(imageID, imageUrl)

        return FavouriteItem(favID, image, userID)
    }
}
