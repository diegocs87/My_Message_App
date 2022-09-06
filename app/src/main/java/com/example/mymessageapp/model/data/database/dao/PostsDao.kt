package com.example.mymessageapp.model.data.database.dao

import androidx.room.*
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity
import com.example.mymessageapp.model.data.database.entities.PostsEntity

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts_table")
    suspend fun getAllPosts(): List<PostsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<PostsEntity>)

    @Delete
    suspend fun deletePosts(posts: PostsEntity)
}