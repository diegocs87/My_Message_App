package com.example.mymessageapp.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.database.PostDataBaseService
import com.example.mymessageapp.model.data.database.entities.PostsEntity
import com.example.mymessageapp.model.network.PostsAPIService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsAPIViewModel @Inject constructor(private val postsService: PostsAPIService)
: ViewModel() {

    val postsModel = MutableLiveData<List<PostsDataItem>>()
    fun getPosts() {
        viewModelScope.launch {
            val postsList = postsService.getPosts()
            postsModel.postValue(postsList)
        }
    }
}