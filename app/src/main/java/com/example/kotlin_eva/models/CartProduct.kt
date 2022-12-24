package com.example.kotlin_eva.models

import org.json.JSONObject
import kotlin.properties.Delegates

class CartProduct {

    lateinit var product: Product
    var id = 0

    companion object {
        @JvmStatic
        fun newInstance(jsonObject: JSONObject) =
            CartProduct().apply {
                val p = jsonObject.getJSONObject("product")
                product = Product.newInstance(p)
                id = jsonObject.getInt("id")
                //if(jsonObject.has("is_in_cart")) isInCart = jsonObject.getBoolean("is_in_cart")
            }
    }

}