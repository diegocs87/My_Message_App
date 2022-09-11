package com.example.mymessageapp.model.data.database

import android.content.Context
import com.example.mymessageapp.model.PostsAPIBuilder
import com.example.mymessageapp.model.data.PostsDataItem
import com.example.mymessageapp.model.data.database.entities.PostsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostDataBaseService @Inject constructor() {
    suspend fun savePosts(context: Context, postsList: List<PostsEntity>) {
        return withContext(Dispatchers.IO){
            val postsDao = PostsDataBase.buildDatabase(context).postsDao()
            postsDao.insertAllPosts(postsList)
        }
    }
}