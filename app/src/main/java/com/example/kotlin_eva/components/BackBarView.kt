package com.example.kotlin_eva.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.kotlin_eva.R

class BackBarView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0, defStyleRes: Int = 0)
    : RelativeLayout(context, attrs, defStyle, defStyleRes) {

        init {
            LayoutInflater.from(context).inflate(R.layout.back_bar_view, this, true)
            attrs?.let {
                val typedArray = context.obtainStyledAttributes(it, R.styleable.BackBarView)

                val _title = typedArray.getString(R.styleable.BackBarView_back_view_title)
                if (_title != null) {
                    val backView = findViewById<BackView>(R.id.backView)
                    backView.title = _title
                }
            }
        }
}