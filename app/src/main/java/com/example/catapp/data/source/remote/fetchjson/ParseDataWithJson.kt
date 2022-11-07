package com.example.catapp.data.source.remote.fetchjson

import com.example.catapp.utils.BREEDS
import com.example.catapp.utils.BREEDS_SEARCH
import com.example.catapp.utils.CATEGORIES_TAG
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ParseDataWithJson {

    fun parseJsonToData(jsonArray: JSONArray?, keyEntity: String): Any {
        val data = mutableListOf<Any>()
        try {
            for (i in 0 until (jsonArray?.length() ?: 0)) {
                val item = parseJsonToObject(jsonArray?.getJSONObject(i), keyEntity)
                if (item != null) {
                    data.add(item)
                }
            }
        } catch (e: JSONException) {
            return e
        }
        return data
    }

    fun parseJsonToObject(jsonObject: JSONObject?, keyEntity: String): Any? {
        var result: Any? = null
        try {
            if (jsonObject != null)  {
                return when(keyEntity) {
                    "" -> ParseCatJson().catParseJson(jsonObject)
                    BREEDS -> ParseBreedsJson().breedParse(jsonObject)
                    CATEGORIES_TAG -> ParseCategoryJson().categoryParse(jsonObject)
                    BREEDS_SEARCH -> ParseBreedsSearchJson().breedParse(jsonObject)
                    else -> null
                }
            }
        } catch (e: JSONException) {
            result = e
        }
        return result
    }
}
