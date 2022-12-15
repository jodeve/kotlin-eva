package com.example.kotlin_eva.components

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.example.kotlin_eva.R

class TextButton(context: Context, attributeSet: AttributeSet): Text(context, attributeSet) {

    init {
        applyFont()
    }

    override fun applyFont() {
        super.applyFont()
        val color = ResourcesCompat.getColor(resources, R.color.black, null)
        setTextColor(color)
    }

}