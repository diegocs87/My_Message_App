package com.example.mymessageapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.database.dao.FavoritesDao
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity
import com.example.mymessageapp.model.data.database.entities.toPostsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeFavoriteStateViewModel @Inject constructor(private val favoritesDao: FavoritesDao):
    ViewModel() {
    val isFavoriteModel = MutableLiveData<Boolean>()
    val favoritesList = MutableLiveData<List<PostsDataItem>>()

    fun getFavoriteState(favPost: FavoritesEntity){
        viewModelScope.launch {
            getFavoriteStateFromDao(favPost)
        }
    }

    fun setFavoriteState(favPost: FavoritesEntity){
        viewModelScope.launch {
            setFavoriteStateFromDao(favPost)
        }
    }

    fun getAllFavorites(){
        viewModelScope.launch {
            getAllFavoritesList()
        }
    }

    suspend fun getAllFavoritesList(){
        val favoriteListEntity = favoritesDao.getAllFavorites()
        favoritesList.postValue(favoriteListEntity.map { it.toPostsData()})
    }

    suspend fun getFavoriteStateFromDao(favPost: FavoritesEntity){
        if(favoritesDao.getFavoriteStatus(favPost.tittle) == null){
            isFavoriteModel.postValue(false)
        }else{
            isFavoriteModel.postValue(true)
        }
    }

    suspend fun setFavoriteStateFromDao(favPost: FavoritesEntity){
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