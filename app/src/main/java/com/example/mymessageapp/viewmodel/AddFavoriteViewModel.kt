package com.example.mymessageapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.database.PostsDataBase
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity
import kotlinx.coroutines.launch

class AddFavoriteViewModel(): ViewModel() {
    fun getFavorite(favPost: FavoritesEntity, context: Context){
        val favoritesDao = PostsDataBase.buildDatabase(context).favoritesDao()
        viewModelScope.launch {
            favoritesDao.insertFavorite(favPost)
        }
    }
}