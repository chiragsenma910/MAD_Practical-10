package com.example.mad_practical_10_106_5b6

import org.json.JSONObject
import java.io.Serializable

class PersonListCardModel(
    jsonObject: JSONObject): Serializable {
    var id:String
    var name: String
    var emailld: String
    var phoneNo: String
    var address: String
    var latitude: Double
    var longitude:Double

    init {
        id = jsonObject.getString("id")
        emailld = jsonObject.getString("email")
        phoneNo = jsonObject.getString("phone")
        val profileJson = jsonObject.getJSONObject("profile")
        name = profileJson.getString("name")
        address = profileJson.getString("address")
        val locationJson = profileJson.getJSONObject("location")
        latitude = locationJson.getDouble("lat")
        longitude = locationJson.getDouble("long")
    }
}