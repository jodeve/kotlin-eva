package com.example.kotlin_eva.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_eva.R
import com.example.kotlin_eva.adapters.OrdersAdapter
import com.example.kotlin_eva.interfaces.OrdersApiListener
import com.example.kotlin_eva.models.Order
import com.example.kotlin_eva.models.Product
import com.example.kotlin_eva.services.OrdersApi

class OrdersFragment: Fragment(), OrdersApiListener {

    var orders = ArrayList<Product>()
    lateinit var ordersRV: RecyclerView
    lateinit var ordersAdapter: OrdersAdapter
    lateinit var progressBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        OrdersApi.onFetchOrders(requireActivity(), this)
            .start()
        return view
    }

    override fun onFinishFetchOrders(orders: ArrayList<Order>) {
        ordersRV = requireView().findViewById(R.id.orders)
        progressBar.visibility = View.GONE
        ordersRV.visibility = View.VISIBLE
        ordersAdapter = OrdersAdapter(requireContext(), orders)
        ordersRV.adapter = ordersAdapter
        ordersRV.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onFinishCreateOrder() {
    }

}