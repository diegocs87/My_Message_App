package com.example.mymessageapp.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData (
    val name: String ,
    val email:String,
    val phone: String,
    val webSite: String
    ): Parcelable