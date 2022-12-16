package com.example.kotlin_eva.components

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.kotlin_eva.R

open class TextFieldView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0, defStyleRes: Int = 0): LinearLayout(context, attrs, defStyle, defStyleRes){

    var labelTextView: TextView
    var errorTextView: TextView
    var editText: EditText

    var isRequired = true
    var input = ""

    var label: String = ""
        set(value) {
            field = value
            labelTextView.text = value
            labelTextView.visibility = VISIBLE
        }

    var error: String = ""
        set(value) {
            field = value
            errorTextView.text = value
            if(value.isNotEmpty()){
                errorTextView.visibility = VISIBLE
                editText.background = ResourcesCompat.getDrawable(resources, R.drawable.text_field_error, null)
            }else{
                errorTextView.visibility = GONE
                editText.background = ResourcesCompat.getDrawable(resources, R.drawable.textfield_edittext, null)
            }
        }


    init {
        LayoutInflater.from(context).inflate(R.layout.text_field_view, this, true)

        labelTextView = findViewById(R.id.textfield_label)
        errorTextView = findViewById(R.id.textfield_error)
        editText = findViewById(R.id.textfield_input)


        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TextFieldView)

            val _label = typedArray.getString(R.styleable.TextFieldView_label)
            if(_label != null)
                label = _label

            val _isRequired = typedArray.getBoolean(R.styleable.TextFieldView_required, true)
            if(!_isRequired) isRequired = false

            val _input = typedArray.getString(R.styleable.TextFieldView_input)
            if(!_input.isNullOrEmpty()) {
                input = _input
                if(input == "email") editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
            }


            typedArray.recycle()
        }
    }

    open fun isValid(): Boolean{
        val text = editText.text
        if(isRequired && text.isEmpty()){
            error = "${label} is required"
            return false
        }

        if(input == "email" && !Patterns.EMAIL_ADDRESS.matcher(text).matches()){
            error = "${label} must be a valid email"
            return false
        }

        error = ""
        return true
    }

}