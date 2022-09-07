package com.example.mymessageapp.model.network

import com.example.mymessageapp.model.PostsAPIBuilder
import com.example.mymessageapp.model.data.CommentsDataItem
import com.example.mymessageapp.model.data.UserDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentsService {
    suspend fun getComment(postId:String): List<CommentsDataItem> {
        return withContext(Dispatchers.IO){
            val response = PostsAPIBuilder.service.getCommentsById(postId)
            val body = response.body()
            body ?: emptyList()
        }
    }
}