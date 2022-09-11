package com.example.mymessageapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.database.PostsDataBase
import com.example.mymessageapp.model.data.database.dao.FavoritesDao
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity
import com.example.mymessageapp.model.data.database.entities.toPostsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeFavoriteStateViewModel @Inject constructor(): ViewModel() {
    val isFavoriteModel = MutableLiveData<Boolean>()
    val favoritesList = MutableLiveData<List<PostsDataItem>>()

    fun getFavoriteState(favPost: FavoritesEntity, context: Context){
        val favoritesDao = PostsDataBase.buildDatabase(context).favoritesDao()
        viewModelScope.launch {
            getFavoriteState(favoritesDao, favPost)
        }
    }

    fun setFavoriteState(favPost: FavoritesEntity, context: Context){
        val favoritesDao = PostsDataBase.buildDatabase(context).favoritesDao()
        viewModelScope.launch {
            setFavoriteState(favoritesDao, favPost)
        }
    }

    fun getAllFavorites(context: Context){
        val favoritesDao = PostsDataBase.buildDatabase(context).favoritesDao()
        viewModelScope.launch {
            getAllFavoritesList(favoritesDao)
        }
    }

    suspend fun getAllFavoritesList(favoritesDao: FavoritesDao){
        val favoriteListEntity = favoritesDao.getAllFavorites()
        favoritesList.postValue(favoriteListEntity.map { it.toPostsData()})
    }

    suspend fun getFavoriteState(favoritesDao: FavoritesDao, favPost: FavoritesEntity){
        if(favoritesDao.getFavoriteStatus(favPost.tittle) == null){
            isFavoriteModel.postValue(false)
        }else{
            isFavoriteModel.postValue(true)
        }
    }

    suspend fun setFavoriteState(favoritesDao: FavoritesDao, favPost: FavoritesEntity){
        if(favoritesDao.getFavoriteStatus(favPost.tittle) == null){
            favoritesDao.insertFavorite(favPost)
            isFavoriteModel.postValue(true)
        }else{
            favoritesDao.deleteFavoriteTittle(favPost.tittle)
            favoritesDao.deleteFavorite(favPost)
            isFavoriteModel.postValue(false)
        }
    }
}