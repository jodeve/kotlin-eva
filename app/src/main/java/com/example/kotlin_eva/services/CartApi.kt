package com.example.kotlin_eva.services

import android.app.Activity
import android.widget.TextView
import com.example.kotlin_eva.R
import com.example.kotlin_eva.interfaces.ICart
import com.example.kotlin_eva.models.AppContext
import com.example.kotlin_eva.models.CartProduct
import com.example.kotlin_eva.models.Product
import org.json.JSONArray

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

    fun index(activity: Activity): Thread{
        return Thread(Runnable {
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
                    val iCart = activity as ICart
                    iCart.onIndex(cartProducts)
                }
            }
        })
    }

    fun remove(activity: Activity, cartProduct: CartProduct, index: Int): Thread{
        return Thread(Runnable {
            val req = Api(activity, "/carts/${cartProduct.id}", "DELETE")
            val res = req.execute()
            if(res.isSuccessful){
                activity.runOnUiThread {
                    val iCart = activity as ICart
                    iCart.onDelete(index)
                    AppContext.reduceCart(activity)
                }
            }
        })
    }

}