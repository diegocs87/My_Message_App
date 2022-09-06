package com.example.mymessageapp.model.network

import com.example.mymessageapp.model.data.PostsDataItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface PostsAPIClient {
    @GET("/posts")
    suspend fun getAllPosts() : Response<List<PostsDataItem>>
}