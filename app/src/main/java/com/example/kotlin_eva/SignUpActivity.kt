package com.example.kotlin_eva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kotlin_eva.components.TextFieldView

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        Statusbar.makeWhite(this)

        val button2 = findViewById<Button>(R.id.button2)

        val nameTextFieldView = findViewById<TextFieldView>(R.id.name)
        val emailTextFieldView = findViewById<TextFieldView>(R.id.email)
        val passwordTextFieldView = findViewById<TextFieldView>(R.id.password)
        val cPasswordTextFieldView = findViewById<TextFieldView>(R.id.c_password)

        button2.setOnClickListener {

        }
    }
}