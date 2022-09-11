package com.example.mymessageapp.model.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymessageapp.model.data.database.dao.FavoritesDao
import com.example.mymessageapp.model.data.database.dao.PostsDao
import com.example.mymessageapp.model.data.database.entities.FavoritesEntity
import com.example.mymessageapp.model.data.database.entities.PostsEntity

@Database(entities = [FavoritesEntity::class, PostsEntity::class], version = 1)
abstract class PostsDataBase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao
    abstract fun postsDao(): PostsDao

    companion object {

        private const val DATABASE_NAME = "my_message_app_db"

        fun buildDatabase(context: Context): PostsDataBase = Room.databaseBuilder(
            context.applicationContext,
            PostsDataBase::class.java,
            DATABASE_NAME
        ).build()
    }
}