package com.example.mymessageapp.model.network

import com.example.mymessageapp.model.MessageData
import retrofit2.Call
import retrofit2.http.GET

interface PostsAPIService {

    @GET("posts")
    fun getAllPosts() : Call<MessageData>
}