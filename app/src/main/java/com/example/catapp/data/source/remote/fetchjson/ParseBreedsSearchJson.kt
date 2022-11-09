package com.example.catapp.data.source.remote.fetchjson

import com.example.catapp.data.model.responsemodel.CatEntry
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.model.responsemodel.breeds.Image
import com.example.catapp.data.model.responsemodel.cat.Weight
import org.json.JSONObject

class ParseBreedsSearchJson {

    fun breedParse(jsonObject: JSONObject): BreedItem {
        val breedName = jsonObject.optString(CatEntry.NAME)
        val origin = jsonObject.optString(CatEntry.ORIGIN)
        val breedID = jsonObject.optString(CatEntry.ID)
        val adaptability = jsonObject.optInt(CatEntry.ADAPT)

        val childFriendly = jsonObject.optInt(CatEntry.CHILD_FRIENDLY)
        val dogFriendly = jsonObject.optInt(CatEntry.DOG_FRIENDLY)
        val wikiURL = jsonObject.optString(CatEntry.WIKI)
        val lifeSpan = jsonObject.optString(CatEntry.LIFE_SPAN)

        val intel = jsonObject.optInt(CatEntry.INTEL)
        val description = jsonObject.optString(CatEntry.DES)
        val health = jsonObject.optInt(CatEntry.HEALTH)
        val metric = jsonObject.getJSONObject(CatEntry.WEIGHT).optString(CatEntry.METRIC)

        val image = Image("", "")

        return BreedItem(
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
            wikiURL,
            image
        )
    }
}
