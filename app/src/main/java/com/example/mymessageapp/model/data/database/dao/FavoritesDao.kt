package com.example.mymessageapp.model.data.database.dao

import androidx.room.*
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity
import com.example.mymessageapp.model.data.database.entities.PostsEntity

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites_table")
    fun getAllFavorites(): List<FavoritesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertFavorite(favorites: FavoritesEntity)

    @Delete
    fun deleteFavorite(posts: FavoritesEntity)
}