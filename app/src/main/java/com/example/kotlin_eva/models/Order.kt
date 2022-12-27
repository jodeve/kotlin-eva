package com.example.kotlin_eva.models

import org.json.JSONObject

class Order {

    var id = 0
    var total = 0

    companion object {
        @JvmStatic
        fun newInstance(jsonObject: JSONObject) =
            Order().apply {
                id = jsonObject.getInt("id")
                total = jsonObject.getInt("total")
            }
    }

}