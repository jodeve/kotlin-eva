package com.example.kotlin_eva.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.kotlin_eva.R

class AccountAsideView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0, defStyleRes: Int = 0):
    LinearLayout(context, attrs, defStyle, defStyleRes) {

    lateinit var accountAsideTitle: Text
    lateinit var accountAsideButton: TextButton

    var title = ""
        set(value) {
            field = value
            accountAsideTitle.text = field
        }

    var buttonText = ""
        set(value){
            field = value
            accountAsideButton.text =field
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.account_aside_view, this, true)
        accountAsideTitle = findViewById(R.id.accountAsideTitle)
        accountAsideButton = findViewById(R.id.accountAsideButton)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.AccountAsideView)

            val _title = typedArray.getString(R.styleable.AccountAsideView_aside_title)
            if (_title != null) {
                title = _title
            }

            val _buttonText = typedArray.getString(R.styleable.AccountAsideView_aside_button_text)
            if (_buttonText != null) {
                buttonText = _buttonText
            }
        }
    }


}