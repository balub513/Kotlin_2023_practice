package com.example.komalkotlin2024.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey(autoGenerate = true)  val id: Int = 0,
    val name: String,
    val password: String)