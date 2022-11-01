package com.example.catapp.data.source.remote.fetchjson

import com.example.catapp.data.model.responsemodel.CatEntry
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.model.responsemodel.breeds.Image
import com.example.catapp.data.model.responsemodel.cat.Weight
import org.json.JSONObject

class ParseBreedsJson {

    fun breedParse(jsonObject: JSONObject): BreedItem {
        val breedName = jsonObject.getString(CatEntry.NAME)
        val origin = jsonObject.getString(CatEntry.ORIGIN)
        val breedID = jsonObject.getString(CatEntry.ID)
        val adaptability = jsonObject.getInt(CatEntry.ADAPT)

        val childFriendly = jsonObject.getInt(CatEntry.CHILD_FRIENDLY)
        val dogFriendly = jsonObject.getInt(CatEntry.DOG_FRIENDLY)
        val wikiURL = jsonObject.getString(CatEntry.WIKI)
        val lifeSpan = jsonObject.getString(CatEntry.LIFE_SPAN)

        val intel = jsonObject.getInt(CatEntry.INTEL)
        val description = jsonObject.getString(CatEntry.DES)
        val health = jsonObject.getInt(CatEntry.HEALTH)
        val metric = jsonObject.getJSONObject(CatEntry.WEIGHT).getString(CatEntry.METRIC)

        val imageUrl = jsonObject.getJSONObject(CatEntry.IMAGE).getString(CatEntry.URL)
        val imageID = jsonObject.getJSONObject(CatEntry.IMAGE).getString(CatEntry.ID)
        val image = Image(imageUrl, imageID)

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
