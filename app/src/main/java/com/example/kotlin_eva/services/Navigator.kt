package com.example.kotlin_eva.services

import android.content.Context
import android.content.Intent

object Navigator {

    fun navigate(context: Context, clazz: Class<*>){
        val intent = Intent(context, clazz)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}