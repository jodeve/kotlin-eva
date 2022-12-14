package com.example.kotlin_eva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kotlin_eva.components.Text
import com.example.kotlin_eva.components.Textfield

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val button2 = findViewById<Button>(R.id.button2)
        val textfield = findViewById<Textfield>(R.id.name)

        button2.setOnClickListener {
            textfield.error = "Name is required"
        }
    }
}