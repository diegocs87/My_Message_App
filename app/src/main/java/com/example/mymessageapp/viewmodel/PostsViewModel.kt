package com.example.mymessageapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.network.PostsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val postsService: PostsService): ViewModel() {
    val postsModel = MutableLiveData<List<PostsDataItem>>()
    fun getPosts(){
        viewModelScope.launch {
            val postsList = postsService.getPosts()
            postsModel.postValue(postsList)
        }
    }
}