package com.example.kotlin_eva.models

import org.json.JSONObject

open class User {

    lateinit var name: String
    lateinit var email: String
    lateinit var avatar: String

    companion object {
        @JvmStatic
        fun newInstance(jsonObject: JSONObject) =
            User().apply {
                name = jsonObject.getString("name")
                email = jsonObject.getString("email")
                //avatar = jsonObject.getString("avatar")
            }
    }
}