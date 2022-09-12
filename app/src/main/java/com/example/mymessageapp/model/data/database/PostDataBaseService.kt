package com.example.mymessageapp.model.data.database

import android.content.Context
import com.example.mymessageapp.model.data.database.entities.PostsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostDataBaseService @Inject constructor() {
    suspend fun savePosts(context: Context, postsList: List<PostsEntity>) {
        withContext(Dispatchers.IO){
            val postsDao = PostsDataBase.buildDatabase(context).postsDao()
            postsDao.insertAllPosts(postsList)
        }
    }

    suspend fun getPosts(context: Context): List<PostsEntity> {
        return withContext(Dispatchers.IO){
            val postsDao = PostsDataBase.buildDatabase(context).postsDao()
            postsDao.getAllPosts()
        }
    }

    suspend fun getDBState(context: Context) : Boolean {
        return withContext(Dispatchers.IO){
            val postsDao = PostsDataBase.buildDatabase(context).postsDao()
            postsDao.getTableState() == null
        }
    }

    suspend fun deleteALL(context: Context) {
        withContext(Dispatchers.IO){
            val postsDao = PostsDataBase.buildDatabase(context).postsDao()
            postsDao.deleteAll()
        }
    }
}