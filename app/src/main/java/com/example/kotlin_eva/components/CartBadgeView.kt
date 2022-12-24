package com.example.kotlin_eva.components

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.kotlin_eva.R
import com.example.kotlin_eva.activities.CartActivity
import com.example.kotlin_eva.models.AppContext
import com.example.kotlin_eva.services.Navigator

class CartBadgeView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0, defStyleRes: Int = 0):
    LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.cart_badge_view, this, true)
        val cartCountLayout = findViewById<RelativeLayout>(R.id.cartLayout)
        cartCountLayout.setOnClickListener {
            Navigator.navigate(context, CartActivity::class.java)
        }
    }



}