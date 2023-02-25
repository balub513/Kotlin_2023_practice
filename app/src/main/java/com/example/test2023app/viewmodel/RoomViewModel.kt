package com.example.test2023app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test2023app.di.module.db_module.UserEntity
import com.example.test2023app.repository.CricRepo
import com.example.test2023app.repository.RoomDBRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(var repo: CricRepo, var dbRepo: RoomDBRepo) :
    BaseViewModel() {

    private var _usersEntitiesLiveData: MutableLiveData<List<UserEntity>> = MutableLiveData()
    var userEntitiesLiveData: MutableLiveData<List<UserEntity>> = _usersEntitiesLiveData

    private var _userStateFlow: MutableStateFlow<UserEntity?> =
        MutableStateFlow(null)
    var userStateFlow: StateFlow<UserEntity?> =
        _userStateFlow

    private var _nameStateFlow: MutableStateFlow<String?> =
        MutableStateFlow(null)
    var nameStateFlow = _nameStateFlow

    private var _ageFlow: MutableStateFlow<String?> =
        MutableStateFlow(null)
    var ageFlow = _ageFlow

    fun addUserInDB(user: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.insertUser(user)
        }
    }

    fun getUsersFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val userEntities = dbRepo.fetchUsers()
            _usersEntitiesLiveData.postValue(userEntities)
        }
    }

    fun getUserByUserId(id: Int) {
        viewModelScope.launch {
            val userEntity = dbRepo.getUserById(id)
            _userStateFlow.value = userEntity
        }
    }


}