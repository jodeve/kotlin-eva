package com.example.kotlin_eva.services

import android.app.Activity
import com.example.kotlin_eva.interfaces.OrdersApiListener
import com.example.kotlin_eva.interfaces.ProductsApiListener
import com.example.kotlin_eva.models.Product
import org.json.JSONArray
import org.json.JSONObject

object OrdersApi {

    fun onCreateOrder(activity: Activity, ordersApiListener: OrdersApiListener): Thread{
        return Thread(Runnable {
            val req = Api(activity, "/orders", HashMap())
            val res = req.execute()
            if(res.isSuccessful){
                activity.runOnUiThread {
                    ordersApiListener.onFinishCreateOrder()
                }
            }
        })
    }

}