package com.example.kotlin_eva.interfaces

import com.example.kotlin_eva.models.Order

interface OrdersApiListener {
    fun onFinishCreateOrder()
    fun onFinishFetchOrders(orders: ArrayList<Order>)
}