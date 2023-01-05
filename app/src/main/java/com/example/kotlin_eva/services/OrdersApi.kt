package com.example.kotlin_eva.services

import android.app.Activity
import com.example.kotlin_eva.interfaces.OrdersApiListener
import com.example.kotlin_eva.models.Order
import com.example.kotlin_eva.models.Product
import org.json.JSONArray
import java.lang.Exception

object OrdersApi {

    fun onFetchOrders(activity: Activity, ordersApiListener: OrdersApiListener): Thread{
        return Thread(Runnable {
            try {
                val req = Api(activity, "/orders")
                val res = req.execute()
                if(res.isSuccessful){
                    val body = res.body()
                    val orders = ArrayList<Order>()
                    val jsonArray = JSONArray(body.string())
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        orders.add(Order.newInstance(jsonObject))
                    }
                    activity.runOnUiThread {
                        ordersApiListener.onFinishFetchOrders(orders)
                    }
                }
            }catch (e: Exception){
                Toaster.makeError(activity)
            }
        })
    }

    fun onCreateOrder(activity: Activity, ordersApiListener: OrdersApiListener): Thread{
        return Thread(Runnable {
            try {
                val req = Api(activity, "/orders", HashMap())
                val res = req.execute()
                if(res.isSuccessful){
                    activity.runOnUiThread {
                        ordersApiListener.onFinishCreateOrder()
                    }
                }
            }catch (e: Exception){
                Toaster.makeError(activity)
            }
        })
    }

}