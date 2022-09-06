package com.example.mymessageapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.UserDataItem
import com.example.mymessageapp.model.network.UsersService
import kotlinx.coroutines.launch

class UsersViewModel: ViewModel() {
    val usersModel = MutableLiveData<UserDataItem>()
    val userService = UsersService()
    fun getUser(userId: String){
        viewModelScope.launch {
            val userList = userService.getUsers(userId)
            usersModel.postValue(userList)
        }
    }
}