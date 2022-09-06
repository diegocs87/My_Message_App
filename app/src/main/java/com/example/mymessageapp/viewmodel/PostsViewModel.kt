package com.example.mymessageapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.network.PostsService
import kotlinx.coroutines.launch

class PostsViewModel: ViewModel() {
    val postsModel = MutableLiveData<List<PostsDataItem>>()
    val postsService = PostsService()
    fun getPosts(){
        viewModelScope.launch {
            val postsList = postsService.getPosts()
            postsModel.postValue(postsList)
        }
    }
}