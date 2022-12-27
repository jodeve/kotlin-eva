package com.example.kotlin_eva.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_eva.adapters.CartProductsAdapter
import com.example.kotlin_eva.R
import com.example.kotlin_eva.interfaces.CartApiListener
import com.example.kotlin_eva.interfaces.OrdersApiListener
import com.example.kotlin_eva.models.AppContext
import com.example.kotlin_eva.models.CartProduct
import com.example.kotlin_eva.services.CartApi
import com.example.kotlin_eva.services.Navigator
import com.example.kotlin_eva.services.OrdersApi
import com.example.kotlin_eva.services.Statusbar

class CartActivity : AppCompatActivity(), CartApiListener, OrdersApiListener {

    lateinit var cartProductsRV: RecyclerView
    lateinit var progressBar: ProgressBar
    var cartProducts = ArrayList<CartProduct>()
    lateinit var cartProductsAdapter: CartProductsAdapter
    lateinit var totalHeader: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        Statusbar.makeWhite(this)
        CartApi.onFetchCartProducts(this)
            .start()
        val backButton = findViewById<ImageView>(R.id.backButton)
        totalHeader = findViewById<TextView>(R.id.total)
        backButton.setOnClickListener {
            finish()
        }
        val checkout = findViewById<Button>(R.id.checkout)
        checkout.setOnClickListener {
            OrdersApi.onCreateOrder(this, this)
                .start()
        }
    }

    fun updateTotal(){
        var total = 0
        cartProducts.forEach {
            val cost = it.product.cost
            total += cost
        }
        totalHeader.text = "GHC ${total.toString()}"
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
        updateTotal()
    }

    override fun onFinishRemoveCartProduct(position: Int) {
        cartProducts.removeAt(position)
        cartProductsAdapter.notifyItemRemoved(position)
        updateTotal()
    }

    override fun onFinishCreateOrder() {
        AppContext.cartCount = 0
        Navigator.navigate(applicationContext, MainActivity::class.java)
    }
}