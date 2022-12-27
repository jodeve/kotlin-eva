package com.example.kotlin_eva.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_eva.R
import com.example.kotlin_eva.components.Header
import com.example.kotlin_eva.models.Order

class OrdersAdapter(var context: Context, var orders: ArrayList<Order>): RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById<Header>(R.id.orderTitle)
        val total: TextView = view.findViewById<Header>(R.id.orderTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.order_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]
        holder.title.text = "Order ID: ${order.id}"
        holder.total.text = "GHC ${order.total.toString()}"
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}