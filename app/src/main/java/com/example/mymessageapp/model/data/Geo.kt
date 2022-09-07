package com.example.mymessageapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geo(
    val lat: String,
    val lng: String
):Parcelable