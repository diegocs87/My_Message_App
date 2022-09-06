package com.example.mymessageapp.model.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymessageapp.model.data.UsersDataItem

@Entity(tableName = "favorites_table")
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
   @ColumnInfo (name = "id") val id : Int = 0,
   @ColumnInfo (name = "tittle") val tittle : String,

 )
