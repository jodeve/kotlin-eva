package com.example.kotlin_eva.services

import android.app.Activity
import com.example.kotlin_eva.activities.MainActivity
import com.example.kotlin_eva.R
import com.example.kotlin_eva.activities.SignUpActivity
import com.example.kotlin_eva.components.Text
import com.example.kotlin_eva.models.AppContext
import com.example.kotlin_eva.models.User
import com.squareup.okhttp.Response
import org.json.JSONObject

object AuthApi {

    fun setCurrentUser(res: Response){
        val body = res.body()
        val jsonObject = JSONObject(body.string())
        AppContext.currentUser = User.newInstance(jsonObject)
    }

    fun signUp(activity: Activity, hashMap: HashMap<String, String>): Thread{
        return Thread(Runnable {
            // make request
            val api = Api(activity,"/register", hashMap)
            val res = api.execute()
            val headers = res.headers()
            val bearer = headers.get("Authorization")
            val token = bearer.split("Bearer ")[1]
            Storage.storeData(activity, "token", token)
            setCurrentUser(res)
            Navigator.navigate(activity, MainActivity::class.java)

        })
    }

    fun validateToken(activity: Activity): Thread{
        return Thread(Runnable {
            val api = Api(activity, "/user")
            val res = api.execute()
            if(res.isSuccessful){
                setCurrentUser(res)
                activity.runOnUiThread {
                    val homeName = activity.findViewById<Text>(R.id.homeName)
                    homeName.text = AppContext.currentUser.name
                }
            }else{
                Navigator.navigate(activity, SignUpActivity::class.java)
            }
        })
    }

}