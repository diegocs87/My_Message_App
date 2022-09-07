package com.example.mymessageapp.model.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymessageapp.model.data.UserDataItem

@Entity(tableName = "posts_table")
data class PostsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "id") val id : Int = 0,
    @ColumnInfo (name = "postid") val postid : Int,
    @ColumnInfo (name = "userid") val userid : Int,
    @ColumnInfo (name = "tittle") val tittle : String,
    @ColumnInfo (name = "body") val body : String,
    @ColumnInfo (name = "comments") val comments : List<String>,
    @ColumnInfo (name = "favorite") val favorite : Boolean,
    @ColumnInfo (name = "user") val user : UserDataItem
)