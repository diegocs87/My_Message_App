package com.example.mymessageapp.model.data.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymessageapp.model.data.database.entities.PostsEntity


interface PostsDao {
    @Query("SELECT * FROM posts_table")
    suspend fun getAllPosts(): List<PostsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(postList: List<PostsEntity>)

    @Delete
    suspend fun deletePost(posts: PostsEntity)

    @Delete
    suspend fun deleteAll(posts: List<PostsEntity>)
}