package com.example.mymessageapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.database.PostDataBaseService
import com.example.mymessageapp.model.data.database.entities.PostsEntity
import com.example.mymessageapp.model.data.database.entities.toPostsData
import com.example.mymessageapp.model.network.PostsAPIService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsDBViewModel @Inject constructor( private val dataBaseService: PostDataBaseService
): ViewModel() {

    val postsDBModel = MutableLiveData<List<PostsDataItem>>()
    val savePostsDBModel = MutableLiveData<Boolean>()

    fun savePosts(context: Context, postsList: List<PostsEntity>){
        viewModelScope.launch {
            dataBaseService.savePosts(context, postsList)
            postsDBModel.postValue(postsList.map { list ->
                list.toPostsData()
            })
        }
    }

    fun getPosts(context: Context){
        viewModelScope.launch {
            val DBPostsList = dataBaseService.getPosts(context)
            postsDBModel.postValue(DBPostsList.map { list -> list.toPostsData() })
        }
    }

    fun getDBState(context: Context){
        viewModelScope.launch {
            savePostsDBModel.postValue(dataBaseService.getDBState(context))
        }
    }

    fun deleteAll(context: Context){
        viewModelScope.launch {
            dataBaseService.deleteALL(context)
            postsDBModel.postValue(emptyList())
            println(dataBaseService.getDBState(context))
        }
    }
}