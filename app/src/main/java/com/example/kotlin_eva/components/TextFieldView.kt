package com.example.kotlin_eva.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.kotlin_eva.R

class TextFieldView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0, defStyleRes: Int = 0): LinearLayout(context, attrs, defStyle, defStyleRes) {

    var labelText: TextView
    var errorText: TextView
    var editText: EditText

    var isRequired = true

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
        LayoutInflater.from(context).inflate(R.layout.text_field_view, this, true)

        labelText = findViewById(R.id.textfield_label)
        errorText = findViewById(R.id.textfield_error)
        editText = findViewById(R.id.textfield_input)


        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TextField)

            val _label = typedArray.getString(R.styleable.TextField_label)
            if(_label != null)
                label = _label

            val _isRequired = typedArray.getBoolean(R.styleable.TextField_required, true)
            if(!_isRequired) isRequired = false

            typedArray.recycle()
        }
    }

    fun isValid(): Boolean{
        if(this.isRequired && editText.text.toString().isEmpty()){
            error = "${label} is required"
            return false
        }

        return false
    }

}