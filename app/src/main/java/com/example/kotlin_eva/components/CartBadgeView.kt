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
import android.widget.TextView
import com.example.kotlin_eva.R
import com.example.kotlin_eva.models.AppContext

class CartBadgeView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0, defStyleRes: Int = 0):
    LinearLayout(context, attrs, defStyle, defStyleRes) {

    var cartCount = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.cart_badge_view, this, true)
    }

}