package com.example.kotlin_eva.interfaces

import com.example.kotlin_eva.models.CartProduct
import com.example.kotlin_eva.models.Product

interface ICart {
    fun onAddToCart()
    fun onIndex(cartProducts: ArrayList<CartProduct>)
    fun onDelete(position: Int)
}