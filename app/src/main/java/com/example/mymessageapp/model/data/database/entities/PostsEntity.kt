package com.example.mymessageapp.model.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymessageapp.model.data.PostsDataItem

@Entity(tableName = "posts_table")
data class PostsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "tittle") val tittle : String,
    @ColumnInfo(name = "body") val body : String,
    @ColumnInfo(name = "userId") val userId : Int = 0,
    @ColumnInfo(name = "postId") val postId : Int = 0,
)

fun PostsDataItem.toDataPostBaseData() = PostsEntity(tittle = title , body = body, userId = userId, postId = id)
fun PostsEntity.toPostsData() = PostsDataItem(title = tittle , body = body, userId = userId, id = postId)