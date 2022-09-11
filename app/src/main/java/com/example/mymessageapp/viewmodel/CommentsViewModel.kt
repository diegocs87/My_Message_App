package com.example.mymessageapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.CommentsDataItem
import com.example.mymessageapp.model.network.CommentsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(private val commentsService: CommentsService): ViewModel() {
    val commentsModel = MutableLiveData<List<CommentsDataItem>>()
    fun getComments(commentId: String){
        viewModelScope.launch {
            val commentsList = commentsService.getComment(commentId)
            commentsModel.postValue(commentsList)
        }
    }
}