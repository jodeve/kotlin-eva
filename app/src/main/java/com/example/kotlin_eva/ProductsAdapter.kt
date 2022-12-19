package com.example.kotlin_eva

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_eva.components.Header
import com.example.kotlin_eva.models.Product
import com.squareup.picasso.Picasso


class ProductsAdapter(var context: Context, var products: ArrayList<Product>): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val header: TextView = view.findViewById<Header>(R.id.productItemName)
        val image = view.findViewById<ImageView>(R.id.productItemImage)
        val cost: Header = view.findViewById<Header>(R.id.productItemCost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.product_layout, parent, false)
        view.setOnClickListener {
            Toast.makeText(context, "Hi", Toast.LENGTH_LONG).show()
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.header.text = products[position].name
        Picasso.get()
            .load(products[position].image)
            .transform(RoundCornersTransform(50f))
            .into(holder.image)
        val cost = products[position].cost
        holder.cost.text = "GHC ${cost.toString()}"
    }

    override fun getItemCount(): Int {
        return products.size
    }
}