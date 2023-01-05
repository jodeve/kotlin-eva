package com.example.kotlin_eva.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin_eva.*
import com.example.kotlin_eva.adapters.SlidesAdapter
import com.example.kotlin_eva.models.Slide
import com.example.kotlin_eva.services.Navigator
import com.example.kotlin_eva.services.SlidesOnPageChangeCallback
import com.example.kotlin_eva.services.Statusbar
import com.example.kotlin_eva.services.Storage

class OnBoardingActivity : AppCompatActivity() {

    var currentPos = 0
    var slider: ViewPager2? = null
    var skipButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        Statusbar.makeWhite(this)
        if(!Storage.getData(this, Companion.ONBOARD_KEY).isNullOrEmpty()) {
            Navigator.navigate(this, MainActivity::class.java)
            finish()
        }
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
            if(currentPos == 2) navigateToSignUp()
            else slider!!.currentItem = currentPos + 1
        }

        slider!!.registerOnPageChangeCallback(SlidesOnPageChangeCallback.getCallback(this))

        skipButton!!.setOnClickListener { navigateToSignUp() }
    }

    fun navigateToSignUp(){
        Storage.storeData(this, Companion.ONBOARD_KEY, "store")
        Navigator.navigate(this, SignUpActivity::class.java)
        finish()
    }

    companion object {
        const val ONBOARD_KEY = "onboard"
    }
}