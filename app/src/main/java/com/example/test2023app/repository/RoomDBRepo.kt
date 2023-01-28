package com.example.test2023app.repository

import com.example.test2023app.di.module.db_module.UserDao
import com.example.test2023app.di.module.db_module.UserEntity
import javax.inject.Inject


class RoomDBRepo @Inject constructor(private val repo: UserDao) {

    suspend fun insertUser(userEntity: UserEntity) = repo.addUser(userEntity)

    suspend fun fetchUsers() = repo.getAllUsers()

}