package com.example.kotlin_eva.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.example.kotlin_eva.R

open class Text(context: Context, attributeSet: AttributeSet) : AppCompatTextView(context, attributeSet) {

    init {
        applyFont()
    }

    protected open fun applyFont() {
        val font = ResourcesCompat.getFont(context, R.font.poppins_regular)
        typeface = font
    }

}