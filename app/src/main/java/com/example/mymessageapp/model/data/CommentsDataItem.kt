package com.example.mymessageapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentsDataItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
):Parcelable