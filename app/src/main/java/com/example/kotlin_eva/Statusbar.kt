package com.example.kotlin_eva

import android.app.Activity
import android.graphics.Color
import android.view.WindowManager

object Statusbar {

    fun makeWhite(activity: Activity){
        val window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.WHITE
    }
}