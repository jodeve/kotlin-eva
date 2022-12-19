package com.example.kotlin_eva.services

import android.app.Activity
import com.example.kotlin_eva.interfaces.Products
import com.example.kotlin_eva.models.Product
import org.json.JSONArray
import org.json.JSONObject

object ProductsApi {

    fun index(activity: Activity, products: Products): Thread{
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
                    products.callback(productsA)
                }
            }
        })
    }

    fun show(activity: Activity, products: Products, id: String): Thread{
        return Thread(Runnable {
            val req = Api(activity, "/products/${id}")
            val res = req.execute()
            if(res.isSuccessful){
                val body = res.body()
                val jsonObject = JSONObject(body.string())
                val product = Product.newInstance(jsonObject)
                activity.runOnUiThread {
                    products.setProduct(product)
                }
            }
        })
    }

}