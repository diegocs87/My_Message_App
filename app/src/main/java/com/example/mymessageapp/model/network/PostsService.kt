package com.example.mymessageapp.model.network

import com.example.mymessageapp.model.PostsAPIBuilder
import com.example.mymessageapp.model.data.PostsDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsService {
    suspend fun getPosts(): List<PostsDataItem> {
        return withContext(Dispatchers.IO){
            val response = PostsAPIBuilder.service.getAllPosts()
            val body = response.execute().body()
            body ?: emptyList()
        }
    }
}