package com.example.test2023app.di.module.db_module

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DBConstants.USER_TABLE)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "email")
    val email: String = ""
)
