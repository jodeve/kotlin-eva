package com.example.kotlin_eva.services

import com.squareup.okhttp.*
import org.json.JSONObject

class Api {

    private lateinit var client: OkHttpClient
    private lateinit var request: Request
    private var host = "http://10.0.2.2:8000/api"

    constructor(){

    }

    constructor(path: String, hashMap: HashMap<String, String>){
        val m2: Map<String, String> = hashMap
        val j = JSONObject(m2)
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val requestBody = RequestBody.create(mediaType, j.toString())
        client = OkHttpClient()
        request = Request.Builder()
            .url("${host}${path}")
            .addHeader("Content-type", "application/json")
            .addHeader("Accept", "application/json")
            .method("POST", requestBody)
            .build()
    }

    fun makeRequest(): Response{
        return client.newCall(request)
            .execute()
    }
}