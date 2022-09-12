package com.example.mymessageapp.model.data.database

import com.example.mymessageapp.model.data.database.dao.PostsDao
import com.example.mymessageapp.model.data.database.entities.PostsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostDataBaseService @Inject constructor(private val postsDao: PostsDao) {
    suspend fun savePosts(postsList: List<PostsEntity>) {
        withContext(Dispatchers.IO){
            postsDao.insertAllPosts(postsList)
        }
    }

    suspend fun getPosts(): List<PostsEntity> {
        return withContext(Dispatchers.IO){
            postsDao.getAllPosts()
        }
    }

    suspend fun getDBState() : Boolean {
        return withContext(Dispatchers.IO){
            postsDao.getTableState() == null
        }
    }

    suspend fun deleteALL() {
        withContext(Dispatchers.IO){
            postsDao.deleteAll()
        }
    }
}