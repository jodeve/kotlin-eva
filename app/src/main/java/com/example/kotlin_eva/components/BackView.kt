package com.example.kotlin_eva.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.kotlin_eva.R

class BackView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0, defStyleRes: Int = 0)
: LinearLayout(context, attrs, defStyle, defStyleRes) {

    var title = ""
        set(value) {
            field = value
            val backTitle = findViewById<TextView>(R.id.backTitle)
            backTitle.text = field
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.back_view, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.BackView)

            val _title = typedArray.getString(R.styleable.BackView_title)
            if (_title != null)
                title = _title
        }

    }
}