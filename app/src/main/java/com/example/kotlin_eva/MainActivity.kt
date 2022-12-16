package com.example.kotlin_eva

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.kotlin_eva.services.Navigator
import com.example.kotlin_eva.services.Storage


class MainActivity : AppCompatActivity() {

    var currentPos = 0
    var slider: ViewPager2? = null
    var skipButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Statusbar.makeWhite(this)
        slider = findViewById(R.id.slider)
        val nextButton = findViewById<Button>(R.id.next)
        skipButton = findViewById(R.id.skip)

        val arrayList = ArrayList<Slide>()

        arrayList.add(Slide(R.mipmap.remote_working, "Find Favourite Items"))
        arrayList.add(Slide(R.mipmap.payment, "Easy and Secure Payment"))
        arrayList.add(Slide(R.mipmap.delivery, "Product Delivery"))

        val slidesAdapter = SlidesAdapter(this, arrayList)
        slider!!.adapter = slidesAdapter
        nextButton.setOnClickListener {
            slider!!.currentItem = currentPos + 1
        }

        slider!!.registerOnPageChangeCallback(SlidesOnPageChangeCallback.getCallback(this))

        skipButton!!.setOnClickListener { navigateToSignUp() }
    }

    fun navigateToSignUp(){
        Navigator.navigate(this, SignUpActivity::class.java)
        finish()
    }

}