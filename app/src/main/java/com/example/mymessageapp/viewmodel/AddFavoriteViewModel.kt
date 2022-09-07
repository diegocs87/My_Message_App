package com.example.mymessageapp.viewmodel

import android.app.Application
import android.content.Context
import android.content.Entity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.database.PostsDataBase
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity
import com.example.mymessageapp.model.data.database.entities.toPostsData
import kotlinx.coroutines.launch

class AddFavoriteViewModel(): ViewModel() {
    lateinit var currentFav: PostsDataItem
    lateinit var currentFavEntity: FavoritesEntity
    fun getFavorite(favPost: FavoritesEntity, context: Context){
        val favoritesDao = PostsDataBase.buildDatabase(context).favoritesDao()
        viewModelScope.launch {
            if(favoritesDao.getFavoriteStatus(favPost.tittle) == null){
                favoritesDao.insertFavorite(favPost)
                println("adding element")
            }else{
                favoritesDao.deleteFavoriteTittle(favPost.tittle)
                favoritesDao.deleteFavorite(favPost)
                println("deleting element")
            }

//            favPost.toPostsData()
//            currentFavEntity = favoritesDao.getAllFavorites()[0]
//            currentFav = currentFavEntity.toPostsData()
        }
    }
}