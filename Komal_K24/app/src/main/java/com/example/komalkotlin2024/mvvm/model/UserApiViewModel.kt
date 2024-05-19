package com.example.komalkotlin2024.mvvm.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserApiViewModel(): ViewModel(){

 var usersStateFlow = MutableStateFlow(listOf(User(1, "komal", "reddy","komalreddy@gmail.com", Address("sirsi"), "1234567", "komal.reddy.com",Company("wipro","koml","bk"))))
    val users = usersStateFlow.asStateFlow()


     fun getUsers(){
        viewModelScope.launch {
            UserApiRepository().getUsers().collect{
               usersStateFlow.value = it
           }
        }
    }

}