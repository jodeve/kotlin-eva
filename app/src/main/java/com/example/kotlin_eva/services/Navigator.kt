package com.example.kotlin_eva.services

import android.content.Context
import android.content.Intent
import android.os.Bundle

object Navigator {

    fun navigate(context: Context, clazz: Class<*>, extras: HashMap<String, String>? = null){
        val intent = Intent(context, clazz)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if(extras != null){
            for (extra in extras){
                intent.putExtra(extra.key, extra.value)
            }
        }
        context.startActivity(intent)
    }

}