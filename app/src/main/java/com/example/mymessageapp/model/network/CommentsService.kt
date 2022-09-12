package com.example.mymessageapp.model.network

import com.example.mymessageapp.model.data.CommentsDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommentsService @Inject constructor(private val APIService: PostsAPIClient) {
    suspend fun getComment(postId:String): List<CommentsDataItem> {
        return withContext(Dispatchers.IO){
            val response = APIService.getCommentsById(postId)
            val body = response.body()
            body ?: emptyList()
        }
    }
}