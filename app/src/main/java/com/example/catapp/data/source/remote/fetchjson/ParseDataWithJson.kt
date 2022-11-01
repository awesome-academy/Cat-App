package com.example.catapp.data.source.remote.fetchjson

import com.example.catapp.utils.BREEDS
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
            e.printStackTrace()
        }
        return data
    }

    private fun parseJsonToObject(jsonObject: JSONObject?, keyEntity: String): Any? {
        try {
            if (jsonObject != null)  {
                return when(keyEntity) {
                    "" -> ParseCatJson().catParseJson(jsonObject)
                    BREEDS -> ParseBreedsJson().breedParse(jsonObject)
                    else -> null
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }
}
