package com.example.mymessageapp.model.data.database.dao

import androidx.room.*
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites_table")
    suspend fun getAllFavorites(): List<FavoritesEntity>

    @Query("SELECT * FROM favorites_table WHERE tittle = :tittle")
    suspend fun getFavoriteStatus(tittle: String): FavoritesEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorites: FavoritesEntity)

    @Query ("DELETE FROM favorites_table WHERE tittle = :tittle")
    suspend fun deleteFavoriteTittle(tittle: String)

    @Delete
    suspend fun deleteFavorite(posts: FavoritesEntity)
}