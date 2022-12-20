package com.example.kotlin_eva.services

import android.app.Activity
import android.widget.TextView
import com.example.kotlin_eva.R
import com.example.kotlin_eva.interfaces.ICart
import com.example.kotlin_eva.models.AppContext

object CartApi {

    fun add(activity: Activity, productId: String): Thread{
        val hashMap = HashMap<String, String>()
        hashMap["productId"] = productId
        return Thread(Runnable {
            val req = Api(activity, "/carts", hashMap)
            val res = req.execute()
            if(res.isSuccessful){
                activity.runOnUiThread {
                    AppContext.updateCart(activity)
                    val iCart = activity as ICart
                    iCart.onAddToCart()
                }
            }
        })
    }

}