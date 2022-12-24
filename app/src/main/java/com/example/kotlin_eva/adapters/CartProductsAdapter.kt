package com.example.kotlin_eva.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_eva.R
import com.example.kotlin_eva.RoundCornersTransform
import com.example.kotlin_eva.components.Header
import com.example.kotlin_eva.models.CartProduct
import com.example.kotlin_eva.services.CartApi
import com.squareup.picasso.Picasso

class CartProductsAdapter(var context: Context, var cartProducts: ArrayList<CartProduct>): RecyclerView.Adapter<CartProductsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val header: TextView = view.findViewById<Header>(R.id.productItemName)
        val image = view.findViewById<ImageView>(R.id.productItemImage)
        val cost: Header = view.findViewById<Header>(R.id.productItemCost)
        val removeFromCart = view.findViewById<ImageView>(R.id.removeFromCart)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.cart_product_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartProduct = cartProducts[position]
        val product = cartProduct.product
        holder.header.text = product.name
        Picasso.get()
            .load(product.image)
            .transform(RoundCornersTransform(50f))
            .into(holder.image)
        val cost = product.cost
        holder.cost.text = "GHC ${cost.toString()}"
        holder.removeFromCart.setOnClickListener {
            CartApi.onRemoveCartProduct(context as Activity, cartProduct, position)
                .start()
        }
    }

    override fun getItemCount(): Int {
        return cartProducts.size
    }
}