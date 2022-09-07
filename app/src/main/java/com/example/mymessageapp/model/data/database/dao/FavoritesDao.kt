package com.example.mymessageapp.model.data.database.dao

import androidx.room.*
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites_table")
    suspend fun getAllFavorites(): List<FavoritesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertFavorite(favorites: FavoritesEntity)

    @Delete
    suspend fun deleteFavorite(posts: FavoritesEntity)
}