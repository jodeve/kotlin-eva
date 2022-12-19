package com.example.kotlin_eva.interfaces

import com.example.kotlin_eva.models.Product

interface Products {
    fun callback(products: ArrayList<Product>)
}