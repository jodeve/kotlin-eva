package com.example.kotlin_eva.services

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences




object Storage {

    val NAME = "Eva"

    fun storeData(activity: Activity, key: String, value: String){
        val sh = activity.getSharedPreferences(NAME, MODE_PRIVATE)
        val editor = sh.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getData(activity: Activity, key: String): String?{
        val sh = activity.getSharedPreferences(NAME, MODE_PRIVATE);
        return sh.getString(key, "")
    }

    fun removeData(activity: Activity, key: String){
        val preferences: SharedPreferences = activity.getSharedPreferences(NAME, MODE_PRIVATE)
        preferences.edit().remove(key).commit()
    }
}