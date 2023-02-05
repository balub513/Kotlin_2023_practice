package com.example.test2023app.paging

import androidx.room.Entity
import androidx.room.PrimaryKey

// https://genicsblog.com/gouravkhunger/pagination-in-android-room-database-using-the-paging-3-library
@Entity(tableName = "Items")
data class Item(@PrimaryKey(autoGenerate = true) val id: Int, val name: String)