package com.example.kotlin_eva.components

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import com.example.kotlin_eva.R

class Header2(context: Context, attributeSet: AttributeSet) : Header(context, attributeSet) {

    init {
        applyFont()
    }

    override fun applyFont() {
        super.applyFont()
        val color = ResourcesCompat.getColor(resources, R.color.black, null)
        setTextColor(color)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 35f)
    }

}