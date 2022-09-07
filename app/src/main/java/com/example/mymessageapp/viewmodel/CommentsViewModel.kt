package com.example.mymessageapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.CommentsDataItem
import com.example.mymessageapp.model.network.CommentsService
import com.example.mymessageapp.model.network.PostsService
import com.example.mymessageapp.model.network.UsersService
import kotlinx.coroutines.launch

class CommentsViewModel: ViewModel() {
    val commentsModel = MutableLiveData<List<CommentsDataItem>>()
    val commentsService = CommentsService()
    fun getComments(commentId: String){
        viewModelScope.launch {
            val commentsList = commentsService.getComment(commentId)
            commentsModel.postValue(commentsList)
        }
    }
}