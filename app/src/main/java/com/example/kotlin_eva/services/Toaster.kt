package com.example.kotlin_eva.services

import android.app.Activity
import android.widget.Toast

object Toaster {

    fun makeError(activity: Activity){
        activity.runOnUiThread {
            Toast.makeText(activity, "An unknown error occurred", Toast.LENGTH_LONG).show()
        }
    }

}