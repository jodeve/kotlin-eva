package com.example.kotlin_eva.components

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import com.example.kotlin_eva.R

class Header(context: Context, attributeSet: AttributeSet) : Text(context, attributeSet) {

    init {
        applyFont()
    }

    override fun applyFont() {
        val font = ResourcesCompat.getFont(context, R.font.poppins_medium)
        typeface = font
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
    }

}