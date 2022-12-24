package com.example.kotlin_eva.interfaces

import com.example.kotlin_eva.models.Product

interface ProductsApiListener {
    fun onFinishFetchProducts(products: ArrayList<Product>)

    fun onFinishFetchProduct(product: Product)
}