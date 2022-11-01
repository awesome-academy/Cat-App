package com.example.catapp.data.source.remote.fetchjson

import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.model.responsemodel.CatEntry
import com.example.catapp.data.model.responsemodel.cat.Breed
import com.example.catapp.data.model.responsemodel.cat.Weight
import com.example.catapp.data.model.responsemodel.categories.CategoriesItem
import org.json.JSONException
import org.json.JSONObject

class ParseCatJson {

    fun catParseJson(jsonObject: JSONObject) = Cat().apply {
        jsonObject.let {
            id = it.getString(CatEntry.ID)
            url = it.getString(CatEntry.URL)
            width = it.getInt(CatEntry.WIDTH)
            height = it.getInt(CatEntry.HEIGHT)

            categories = try {
                val categoryInfo = it.getJSONArray(CatEntry.CATEGORIES).getJSONObject(1)
                val categoryName = categoryInfo.getString(CatEntry.NAME)
                val categoryID = categoryInfo.getInt(CatEntry.ID)
                CategoriesItem(categoryID, categoryName)

            } catch (e: JSONException) {
                null
            }
        }

        breed = try {
            catBreedParse(jsonObject)
        } catch (e: JSONException) {
            null
        }
    }

    private fun catBreedParse(jsonObject: JSONObject): Breed? {
        var breed: Breed? = null
        val breedBox = jsonObject.getJSONArray(CatEntry.BREEDS)
        if (breedBox.length() > 0) {
            val breedInfo = jsonObject.getJSONArray(CatEntry.BREEDS).getJSONObject(0)

            val breedName = breedInfo.getString(CatEntry.NAME)
            val origin = breedInfo.getString(CatEntry.ORIGIN)
            val breedID = breedInfo.getString(CatEntry.ID)
            val adaptability = breedInfo.getInt(CatEntry.ADAPT)
            val childFriendly = breedInfo.getInt(CatEntry.CHILD_FRIENDLY)
            val dogFriendly = breedInfo.getInt(CatEntry.DOG_FRIENDLY)
            val wikiURL = breedInfo.getString(CatEntry.WIKI)
            val lifeSpan = breedInfo.getString(CatEntry.LIFE_SPAN)
            val intel = breedInfo.getInt(CatEntry.INTEL)
            val description = breedInfo.getString(CatEntry.DES)
            val health = breedInfo.getInt(CatEntry.HEALTH)

            val weight = breedInfo.getJSONObject(CatEntry.WEIGHT)
            val metric = weight.getString(CatEntry.METRIC)

            breed = Breed(
                adaptability,
                childFriendly,
                description,
                dogFriendly,
                health,
                breedID,
                intel,
                lifeSpan,
                breedName,
                origin,
                Weight(metric),
                wikiURL
            )
        }
        return breed
    }
}
