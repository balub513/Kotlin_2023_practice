package com.example.komalkotlin2024.mvvm.model

import com.example.komalkotlin2024.retrofit.UserApiClient
import kotlinx.coroutines.flow.flowOf

class UserApiRepository() {

    suspend fun getUsers() = flowOf(
        UserApiClient.provideUserApiInterface().getUsers())
    }
