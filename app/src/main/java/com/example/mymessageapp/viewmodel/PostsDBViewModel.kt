package com.example.mymessageapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.database.PostDataBaseService
import com.example.mymessageapp.model.data.database.entities.PostsEntity
import com.example.mymessageapp.model.data.database.entities.toPostsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsDBViewModel @Inject constructor( private val dataBaseService: PostDataBaseService
): ViewModel() {

    val postsDBModel = MutableLiveData<List<PostsDataItem>>()
    val savePostsDBModel = MutableLiveData<Boolean>()

    fun savePosts(postsList: List<PostsEntity>){
        viewModelScope.launch {
            dataBaseService.savePosts(postsList)
            postsDBModel.postValue(postsList.map { list ->
                list.toPostsData()
            })
        }
    }

    fun getPosts(){
        viewModelScope.launch {
            val DBPostsList = dataBaseService.getPosts()
            postsDBModel.postValue(DBPostsList.map { list -> list.toPostsData() })
        }
    }

    fun getDBState(){
        viewModelScope.launch {
            savePostsDBModel.postValue(dataBaseService.getDBState())
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            dataBaseService.deleteALL()
            postsDBModel.postValue(emptyList())
            println(dataBaseService.getDBState())
        }
    }
}