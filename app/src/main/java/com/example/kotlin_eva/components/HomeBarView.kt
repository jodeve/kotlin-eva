package com.example.kotlin_eva.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.kotlin_eva.R

class HomeBarView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0, defStyleRes: Int = 0)
    : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.home_bar_view, this, true)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.HomeBarView)

            val _title = typedArray.getString(R.styleable.HomeBarView_home_bar_title)
            if (_title != null) {
                val title = findViewById<Header>(R.id.home_bar_title)
                title.text = _title
            }
        }
    }
}