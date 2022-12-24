package com.example.kotlin_eva.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_eva.adapters.CartProductsAdapter
import com.example.kotlin_eva.R
import com.example.kotlin_eva.interfaces.CartApiListener
import com.example.kotlin_eva.models.CartProduct
import com.example.kotlin_eva.services.CartApi
import com.example.kotlin_eva.services.Statusbar

class CartActivity : AppCompatActivity(), CartApiListener {

    lateinit var cartProductsRV: RecyclerView
    lateinit var progressBar: ProgressBar
    var cartProducts = ArrayList<CartProduct>()
    lateinit var cartProductsAdapter: CartProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        Statusbar.makeWhite(this)
        CartApi.onFetchCartProducts(this)
            .start()
        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onFinishAddCartProduct() {
    }

    override fun onFinishFetchCartProducts(nCartProducts: ArrayList<CartProduct>) {
        cartProducts = nCartProducts
        cartProductsRV = findViewById<RecyclerView>(R.id.cartProducts)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE
        cartProductsRV.visibility = View.VISIBLE
        cartProductsAdapter = CartProductsAdapter(this, cartProducts)
        cartProductsRV.adapter = cartProductsAdapter
        cartProductsRV.layoutManager = LinearLayoutManager(this)
    }

    override fun onFinishRemoveCartProduct(position: Int) {
        cartProducts.removeAt(position)
        cartProductsAdapter.notifyItemRemoved(position)
    }
}