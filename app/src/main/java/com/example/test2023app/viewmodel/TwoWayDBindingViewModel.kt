package com.example.test2023app.viewmodel

import android.app.Application
import com.example.test2023app.di.MyApplication
import com.example.test2023app.model.User
import com.example.test2023app.repository.RoomDBRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class TwoWayDBindingViewModel @Inject constructor(application: Application, repo: RoomDBRepo) :
    BaseViewModel(application) {

    var userObjStateFlow: MutableStateFlow<User?> = MutableStateFlow(null)
    var userNameFlow: MutableStateFlow<String> = MutableStateFlow("")
    var emailFlow: MutableStateFlow<String> = MutableStateFlow("")
    var passwordFlow: MutableStateFlow<String> = MutableStateFlow("")
    var nationalityFlow: MutableStateFlow<Nation> = MutableStateFlow(Nation.INDIA)
    var genderFlow: MutableStateFlow<Gender> = MutableStateFlow(Gender.MALE)

    fun setNation(nation: Nation) {
        nationalityFlow.value = nation

    }

    fun setGender(gender: Gender) {
        genderFlow.value = gender
    }

    fun registerButtonClicked() {

    }




}