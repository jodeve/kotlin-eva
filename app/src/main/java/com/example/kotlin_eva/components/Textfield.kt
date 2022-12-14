package com.example.kotlin_eva.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.kotlin_eva.R

class Textfield @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0, defStyleRes: Int = 0):
    LinearLayout(context, attrs, defStyle, defStyleRes) {

    var labelText: TextView
    var errorText: TextView
    var editText: EditText

    var label: String = ""
        set(value) {
            field = value
            labelText.text = value
            labelText.visibility = VISIBLE
        }

    var error: String = ""
        set(value) {
            field = value
            errorText.text = value
            errorText.visibility = VISIBLE
            editText.background = ResourcesCompat.getDrawable(resources, R.drawable.text_field_error, null)
        }


    init {
        LayoutInflater.from(context).inflate(R.layout.textfield, this, true)

        labelText = findViewById(R.id.textfield_label)
        errorText = findViewById(R.id.textfield_error)
        editText = findViewById(R.id.textfield_input)


        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.Textfield)

            val _label = typedArray.getString(R.styleable.Textfield_label)
            if(_label != null)
                label = _label
            typedArray.recycle()
        }
    }


    }