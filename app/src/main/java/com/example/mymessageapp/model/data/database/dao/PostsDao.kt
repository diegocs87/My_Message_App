package com.example.mymessageapp.model.data.database.dao

import androidx.room.*
import com.example.mymessageapp.model.data.database.entities.PostsEntity

@Dao
interface PostsDao {
    @Query("SELECT * FROM posts_table")
    suspend fun getAllPosts(): List<PostsEntity>

    @Query("SELECT * FROM posts_table ORDER BY id LIMIT 1")
    suspend fun getTableState(): PostsEntity

    @Query("DELETE FROM posts_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(postList: List<PostsEntity>)

    @Delete
    suspend fun deletePost(posts: PostsEntity)

    @Delete
    suspend fun deleteAll(posts: List<PostsEntity>)
}