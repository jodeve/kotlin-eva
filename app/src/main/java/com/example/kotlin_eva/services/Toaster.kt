package com.example.kotlin_eva.services

import android.app.Activity
import android.widget.Toast

object Toaster {

    fun makeToast(activity: Activity, message: String){
        activity.runOnUiThread {
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }
    }

    fun makeError(activity: Activity){
        makeToast(activity, "An unknown error occurred")
    }

}