package com.example.kotlin_eva.models

import org.json.JSONObject

class Product {
    var id = 0
    var name = ""
    var image = ""
    var cost = 0
    var isInCart = false

    constructor()

    constructor(name: String){
        this.name = name
    }

    fun costString(): String{
        return "GHC ${cost.toString()}"
    }

    companion object {
        @JvmStatic
        fun newInstance(jsonObject: JSONObject) =
            Product().apply {
                name = jsonObject.getString("name")
                image = jsonObject.getString("image")
                cost = jsonObject.getInt("cost")
                id = jsonObject.getInt("id")
                if(jsonObject.has("is_in_cart")) isInCart = jsonObject.getBoolean("is_in_cart")
            }
    }

}