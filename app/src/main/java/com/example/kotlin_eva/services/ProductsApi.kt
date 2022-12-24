package com.example.kotlin_eva.services

import android.app.Activity
import com.example.kotlin_eva.interfaces.ProductsApiListener
import com.example.kotlin_eva.models.Product
import org.json.JSONArray
import org.json.JSONObject

object ProductsApi {

    fun onFetchProducts(activity: Activity, productsApiListener: ProductsApiListener): Thread{
        return Thread(Runnable {
            val req = Api(activity, "/products")
            val res = req.execute()
            if(res.isSuccessful){
                val body = res.body()
                val productsA = ArrayList<Product>()
                val jsonArray = JSONArray(body.string())
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    productsA.add(Product.newInstance(jsonObject))
                }
                activity.runOnUiThread {
                    productsApiListener.onFinishFetchProducts(productsA)
                }
            }
        })
    }

    fun onFetchProduct(activity: Activity, productsApiListener: ProductsApiListener, id: String): Thread{
        return Thread(Runnable {
            val req = Api(activity, "/products/${id}")
            val res = req.execute()
            if(res.isSuccessful){
                val body = res.body()
                val jsonObject = JSONObject(body.string())
                val product = Product.newInstance(jsonObject)
                activity.runOnUiThread {
                    productsApiListener.onFinishFetchProduct(product)
                }
            }
        })
    }

}