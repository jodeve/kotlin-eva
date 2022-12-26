package com.example.kotlin_eva.components

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.kotlin_eva.R

open class OptionsItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0, defStyleRes: Int = 0): LinearLayout(context, attrs, defStyle, defStyleRes){

    lateinit var textView: TextView

    var title = ""
        set(value) {
            field = value
            textView.text = field
        }

    init{
        LayoutInflater.from(context).inflate(R.layout.options_item_view, this, true)

        textView = findViewById(R.id.optionsTitle)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.OptionsItemView)

            val _title = typedArray.getString(R.styleable.OptionsItemView_options_title)
            if(_title != null)
                title = _title

            typedArray.recycle()
        }
    }

}