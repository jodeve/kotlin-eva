package com.example.kotlin_eva.services

import android.app.Activity
import com.squareup.okhttp.*
import org.json.JSONObject

class Api {

    private lateinit var client: OkHttpClient
    private lateinit var request: Request
    private var host = "https://kotlin-eva.herokuapp.com/api"
    private lateinit var activity: Activity

    fun buildRequest(path: String): Request.Builder{
        val token = Storage.getData(activity, "token")
        return Request.Builder()
            .url("${host}${path}")
            .addHeader("Content-type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "Bearer ${token}")
    }

    constructor(activity: Activity, path: String){
        this.activity = activity
        client = OkHttpClient()
        request = buildRequest(path)
            .build()
    }

    constructor(activity: Activity, path: String, method: String){
        this.activity = activity
        client = OkHttpClient()
        request = buildRequest(path)
            .method(method, null)
            .build()
    }

    constructor(activity: Activity, path: String, hashMap: HashMap<String, String>){
        this.activity = activity
        val m2: Map<String, String> = hashMap
        val j = JSONObject(m2)
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val requestBody = RequestBody.create(mediaType, j.toString())
        client = OkHttpClient()
        request = buildRequest(path)
            .method("POST", requestBody)
            .build()
    }

    fun execute(): Response{
        return client.newCall(request)
            .execute()
    }
}