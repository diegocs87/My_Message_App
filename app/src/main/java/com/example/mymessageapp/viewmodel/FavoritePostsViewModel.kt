package com.example.mymessageapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.database.PostsDataBase
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class FavoritePostsViewModel: ViewModel() {


    fun addPostToFavorite(postId: FavoritesEntity, context: Context){
        viewModelScope.launch  {
            val favoritesDao = PostsDataBase.buildDatabase(context).favoritesDao()
            favoritesDao.insertFavorite(postId)
        }
    }

}