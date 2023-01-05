package com.example.kotlin_eva.models

import android.app.Activity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.kotlin_eva.R
import com.example.kotlin_eva.fragments.ActivityFragment

object AppContext {

    lateinit var currentUser: User
    var cartCount = 0

    private lateinit var dialogFragment: DialogFragment

    fun setCartCount(value: Int, activity: Activity) {
        val cartBadgeCount = activity.findViewById<TextView>(R.id.cartCount)
        val cartCountLayout = activity.findViewById<LinearLayout>(R.id.cartCountLayout)
        if(cartBadgeCount != null){
            cartCount = value
            cartBadgeCount.text = cartCount.toString()
            if(cartCount == 0) cartCountLayout.visibility = View.GONE
            else cartCountLayout.visibility = View.VISIBLE
        }else cartCount = value
    }

    fun updateCart(activity: Activity){
        setCartCount(AppContext.cartCount + 1, activity)
    }

    fun reduceCart(activity: Activity){
        setCartCount(AppContext.cartCount - 1, activity)
    }

    fun showActivity(activity: AppCompatActivity){
        dialogFragment = ActivityFragment()
        val ft = activity.supportFragmentManager
            .beginTransaction()
        dialogFragment.isCancelable = false
        dialogFragment.show(ft, "Dialog")
    }

    fun hideActivity(){
        dialogFragment.dismiss()
    }

}