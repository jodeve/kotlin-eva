package com.example.kotlin_eva.services

import android.app.Activity
import com.example.kotlin_eva.activities.MainActivity
import com.example.kotlin_eva.R
import com.example.kotlin_eva.activities.SignUpActivity
import com.example.kotlin_eva.components.Text
import com.example.kotlin_eva.interfaces.AuthApiListener
import com.example.kotlin_eva.models.AppContext
import com.example.kotlin_eva.models.User
import com.squareup.okhttp.Headers
import com.squareup.okhttp.Response
import org.json.JSONObject

object AuthApi {

    private fun setCurrentUser(res: Response, activity: Activity){
        val body = res.body()
        val jsonObject = JSONObject(body.string())
        AppContext.currentUser = User.newInstance(jsonObject)
        val count = jsonObject.getInt("cart")
        activity.runOnUiThread {
            AppContext.setCartCount(count, activity)
        }
    }

    private fun setHeaders(activity: Activity, res: Response){
        val bearer = res.headers().get("Authorization")
        val token = bearer.split("Bearer ")[1]
        Storage.storeData(activity, "token", token)
        setCurrentUser(res, activity)
        Navigator.navigate(activity, MainActivity::class.java)
        activity.finish()
    }

    fun signUp(activity: Activity, hashMap: HashMap<String, String>): Thread{
        return Thread(Runnable {
            // make request
            val api = Api(activity,"/register", hashMap)
            val res = api.execute()
            if(res.isSuccessful) setHeaders(activity, res)

        })
    }

    fun validateToken(activity: Activity): Thread{
        return Thread(Runnable {
            val api = Api(activity, "/user")
            val res = api.execute()
            if(res.isSuccessful){
                setCurrentUser(res, activity)
                activity.runOnUiThread {
                    val authApiListener = activity as AuthApiListener
                    authApiListener.onFinishValidateToken()
                }
            }else{
                Navigator.navigate(activity, SignUpActivity::class.java)
            }
        })
    }


    fun login(activity: Activity, hashMap: HashMap<String, String>): Thread{
        return Thread(Runnable {
            // make request
            val api = Api(activity,"/login", hashMap)
            val res = api.execute()
            if(res.isSuccessful) setHeaders(activity, res)


        })
    }

}