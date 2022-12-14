package com.example.kotlin_eva.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class FlatButton(context: Context, attributeSet: AttributeSet) : BaseButton(context, attributeSet, android.R.attr.borderlessButtonStyle) {
    init {
        var color = Color.BLACK
        this.setTextColor(color)
    }
}