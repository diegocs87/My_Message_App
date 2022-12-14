package com.example.mymessageapp.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MessageData (
    val Tittle: String ,
    val description:String,
    val user: UserData ,
    val comments: String
    ): Parcelable