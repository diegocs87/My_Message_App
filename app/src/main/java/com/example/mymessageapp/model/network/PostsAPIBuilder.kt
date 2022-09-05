package com.example.mymessageapp.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostsAPIBuilder {
    fun retrofitObjectBulder() : Retrofit = Retrofit.Builder().
    baseUrl("https://jsonplaceholder.typicode.com/").
    addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofitObjectBulder().create(PostsAPIService::class.java)
}