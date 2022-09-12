package com.example.mymessageapp.model.network

import com.example.mymessageapp.model.data.UserDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersService @Inject constructor(private val APIService: PostsAPIClient) {
    suspend fun getUsers(userId:String): UserDataItem {
        return withContext(Dispatchers.IO){
            val response = APIService.getUserById(userId)
            val body = response.body()
            body!!
        }
    }
}