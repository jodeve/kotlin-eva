package com.example.kotlin_eva.interfaces

import com.example.kotlin_eva.models.CartProduct
import com.example.kotlin_eva.models.Product

interface CartApiListener {
    fun onFinishAddCartProduct()
    fun onFinishFetchCartProducts(cartProducts: ArrayList<CartProduct>)
    fun onFinishRemoveCartProduct(position: Int)
}