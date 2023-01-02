package com.example.kotlin_eva.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kotlin_eva.R
import com.example.kotlin_eva.components.AccountAsideView
import com.example.kotlin_eva.components.TextFieldView
import com.example.kotlin_eva.services.AuthApi
import com.example.kotlin_eva.services.Navigator
import com.example.kotlin_eva.services.Statusbar

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Statusbar.makeWhite(this)

        val login = findViewById<Button>(R.id.login)

        val arrayList = ArrayList<TextFieldView>()

        val emailTextFieldView = findViewById<TextFieldView>(R.id.email)
        val passwordTextFieldView = findViewById<TextFieldView>(R.id.password)
        val signUpAside = findViewById<AccountAsideView>(R.id.signUpAside)

        arrayList.add(emailTextFieldView)
        arrayList.add(passwordTextFieldView)

        login.setOnClickListener {
            val valid = ArrayList<Boolean>()
            val values = HashMap<String, String>()

            for (textField in arrayList){
                valid.add(textField.isValid())
                values[textField.name] = textField.editText.text.toString()
            }
            if(valid.contains(false)) return@setOnClickListener
            AuthApi.login(this, values)
                .start()
        }

        signUpAside.setOnClickListener {
            Navigator.navigate(applicationContext, SignUpActivity::class.java)
            finish()
        }
    }
}