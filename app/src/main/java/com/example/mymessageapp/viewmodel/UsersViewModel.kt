package com.example.mymessageapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessageapp.model.data.UserDataItem
import com.example.mymessageapp.model.network.UsersService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val userService:UsersService): ViewModel() {
    val usersModel = MutableLiveData<UserDataItem>()
    fun getUser(userId: String){
        viewModelScope.launch {
            val userList = userService.getUsers(userId)
            usersModel.postValue(userList)
        }
    }
}