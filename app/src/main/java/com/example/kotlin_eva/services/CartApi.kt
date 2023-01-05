package com.example.kotlin_eva.services

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_eva.interfaces.CartApiListener
import com.example.kotlin_eva.models.AppContext
import com.example.kotlin_eva.models.CartProduct
import org.json.JSONArray
import java.lang.Exception

object CartApi {

    fun onAddCartProduct(activity: AppCompatActivity, productId: String): Thread{
        val hashMap = HashMap<String, String>()
        hashMap["productId"] = productId
        return Thread(Runnable {
            try {
                AppContext.showActivity(activity)
                val req = Api(activity, "/carts", hashMap)
                val res = req.execute()
                AppContext.hideActivity()
                if(res.isSuccessful){
                    activity.runOnUiThread {
                        AppContext.updateCart(activity)
                        val cartApiListener = activity as CartApiListener
                        cartApiListener.onFinishAddCartProduct()
                    }
                }
            }catch (e: Exception){
                Toaster.makeError(activity)
            }
        })
    }

    fun onFetchCartProducts(activity: Activity): Thread{
        return Thread(Runnable {
            try {
                val req = Api(activity, "/carts")
                val res = req.execute()
                if(res.isSuccessful){
                    var body = res.body().string()
                    val jsonArray = JSONArray(body)
                    val cartProducts = ArrayList<CartProduct>()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        cartProducts.add(CartProduct.newInstance(jsonObject))
                    }
                    activity.runOnUiThread {
                        val cartApiListener = activity as CartApiListener
                        cartApiListener.onFinishFetchCartProducts(cartProducts)
                    }
                }
            }catch (e: Exception){
                Toaster.makeError(activity)
            }
        })
    }

    fun onRemoveCartProduct(activity: AppCompatActivity, cartProduct: CartProduct, index: Int): Thread{
        return Thread(Runnable {
            try {
                AppContext.showActivity(activity)
                val req = Api(activity, "/carts/${cartProduct.id}", "DELETE")
                val res = req.execute()
                AppContext.hideActivity()
                if(res.isSuccessful){
                    activity.runOnUiThread {
                        val cartApiListener = activity as CartApiListener
                        cartApiListener.onFinishRemoveCartProduct(index)
                        AppContext.reduceCart(activity)
                    }
                }
            }catch (e: Exception){
                Toaster.makeError(activity)
            }
        })
    }

}