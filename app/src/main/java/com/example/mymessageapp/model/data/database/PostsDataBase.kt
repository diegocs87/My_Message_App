package com.example.mymessageapp.model.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymessageapp.model.data.database.dao.FavoritesDao
import com.example.mymessageapp.model.data.database.dao.PostsDao
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity
import com.example.mymessageapp.model.data.database.entities.PostsEntity

@Database(entities = [PostsEntity::class, FavoritesEntity::class], version = 1)
abstract class PostsDataBase : RoomDatabase() {

    abstract fun postsDao(): PostsDao
    abstract fun favoritesDao(): FavoritesDao

    companion object {

        private const val DATABASE_NAME = "my_message_app_db"

        fun buildDatabase(context: Context): PostsDataBase = Room.databaseBuilder(
            context.applicationContext,
            PostsDataBase::class.java,
            DATABASE_NAME
        ).build()
    }
}