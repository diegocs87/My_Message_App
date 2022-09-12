package com.example.mymessageapp.hilt

import android.content.Context
import androidx.room.Room
import com.example.mymessageapp.model.data.database.PostsDataBase
import com.example.mymessageapp.model.data.database.dao.FavoritesDao
import com.example.mymessageapp.model.data.database.dao.PostsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "my_message_app_db"

    @Singleton
    @Provides
    fun DBProvider(@ApplicationContext context: Context): PostsDataBase = Room.databaseBuilder(
        context, PostsDataBase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun favDAOProvider(postsDB: PostsDataBase): FavoritesDao = postsDB.favoritesDao()

    @Singleton
    @Provides
    fun postDAOProvider(postsDB: PostsDataBase): PostsDao = postsDB.postsDao()
}