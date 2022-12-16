package com.example.kotlin_eva.services

import android.app.Activity
import com.example.kotlin_eva.MainActivity
import com.example.kotlin_eva.models.AppContext
import com.example.kotlin_eva.models.User
import org.json.JSONObject

object AuthApi {

    fun signUp(activity: Activity, hashMap: HashMap<String, String>): Thread{
        return Thread(Runnable {
            // make request
            val api = Api("/register", hashMap)
            val res = api.makeRequest()
            val headers = res.headers()
            val bearer = headers.get("Authorization")
            val token = bearer.split("Bearer ")[1]
            Storage.storeData(activity, "token", token)
            val body = res.body()
            val jsonObject = JSONObject(body.string())
            AppContext.currentUser = User.newInstance(jsonObject)
            Navigator.navigate(activity, MainActivity::class.java)

        })
    }

}