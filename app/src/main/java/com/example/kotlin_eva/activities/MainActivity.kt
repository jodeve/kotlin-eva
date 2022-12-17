package com.example.kotlin_eva.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_eva.R
import com.example.kotlin_eva.services.Statusbar
import com.example.kotlin_eva.services.AuthApi


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Statusbar.makeWhite(this)
        AuthApi.validateToken(this)
            .start()
    }

}