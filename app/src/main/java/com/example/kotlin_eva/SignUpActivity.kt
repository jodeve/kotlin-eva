package com.example.kotlin_eva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kotlin_eva.components.TextfieldView

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        Statusbar.makeWhite(this)

        val button2 = findViewById<Button>(R.id.button2)

        val nameTextfieldView = findViewById<TextfieldView>(R.id.name)
        val emailTextfieldView = findViewById<TextfieldView>(R.id.email)
        val passwordTextfieldView = findViewById<TextfieldView>(R.id.password)
        val cPasswordTextfieldView = findViewById<TextfieldView>(R.id.c_password)

        button2.setOnClickListener {

        }
    }
}