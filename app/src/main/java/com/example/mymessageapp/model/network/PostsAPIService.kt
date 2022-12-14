package com.example.mymessageapp.model.network

import com.example.mymessageapp.model.data.PostsDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsAPIService @Inject constructor(private val APIService: PostsAPIClient) {
    suspend fun getPosts(): List<PostsDataItem> {
        return withContext(Dispatchers.IO){
            val response = APIService.getAllPosts()
            val body = response.body()
            body ?: emptyList()
        }
    }
}