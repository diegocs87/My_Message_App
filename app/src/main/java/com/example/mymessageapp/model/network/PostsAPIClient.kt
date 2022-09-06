package com.example.mymessageapp.model.network

import com.example.mymessageapp.model.data.CommentsDataItem
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.UserDataItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsAPIClient {
    @GET("/posts")
    suspend fun getAllPosts() : Response<List<PostsDataItem>>

    @GET("/users/{userId}")
    suspend fun getUserById(@Path("userId") userId: String) : Response<UserDataItem>

    @GET("/posts/{postId}/comments")
    suspend fun getCommentsById(@Path("postId") postId: String) : Response<List<CommentsDataItem>>
}