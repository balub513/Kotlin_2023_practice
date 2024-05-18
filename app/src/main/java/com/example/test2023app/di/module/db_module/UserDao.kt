package com.example.test2023app.di.module.db_module

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(userEntity: UserEntity)

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Delete
    suspend fun deleteUser(userEntity: UserEntity)

    @Query("SELECT * FROM ${DBConstants.USER_TABLE} ORDER BY name DESC")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM ${DBConstants.USER_TABLE} WHERE userId LIKE :id")
    suspend fun getUserByID(id: Int): UserEntity

}