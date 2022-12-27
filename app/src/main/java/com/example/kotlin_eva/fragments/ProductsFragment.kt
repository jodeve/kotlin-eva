package com.example.kotlin_eva.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_eva.R
import com.example.kotlin_eva.adapters.ProductsAdapter
import com.example.kotlin_eva.interfaces.ProductsApiListener
import com.example.kotlin_eva.models.AppContext
import com.example.kotlin_eva.models.Product
import com.example.kotlin_eva.services.ProductsApi

class ProductsFragment : Fragment(), ProductsApiListener {

    val products = ArrayList<Product>()
    lateinit var productsRV: RecyclerView
    lateinit var productsAdapter: ProductsAdapter
    lateinit var progressBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        ProductsApi.onFetchProducts(requireActivity(), this)
            .start()
        return view
    }

    override fun onFinishFetchProducts(products: ArrayList<Product>) {
        productsRV = requireView().findViewById(R.id.products)
        progressBar.visibility = View.GONE
        productsRV.visibility = View.VISIBLE
        productsAdapter = ProductsAdapter(requireContext(), products)
        productsRV.adapter = productsAdapter
        productsRV.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onFinishFetchProduct(product: Product) {
    }

    override fun onResume() {
        super.onResume()
        AppContext.setCartCount(AppContext.cartCount, requireActivity())
    }
}