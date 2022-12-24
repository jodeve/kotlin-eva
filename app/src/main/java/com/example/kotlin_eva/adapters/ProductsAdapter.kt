package com.example.kotlin_eva.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_eva.R
import com.example.kotlin_eva.RoundCornersTransform
import com.example.kotlin_eva.activities.ProductActivity
import com.example.kotlin_eva.components.Header
import com.example.kotlin_eva.models.Product
import com.example.kotlin_eva.services.Navigator
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
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.header.text = product.name
        Picasso.get()
            .load(product.image)
            .transform(RoundCornersTransform(50f))
            .into(holder.image)
        val cost = product.cost
        holder.cost.text = "GHC ${cost.toString()}"
        holder.itemView.setOnClickListener {
            val hashMap = HashMap<String, String>()
            hashMap["productId"] = product.id.toString()
            Navigator.navigate(context, ProductActivity::class.java, hashMap)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}