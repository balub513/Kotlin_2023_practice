package com.example.test2023app.utils

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.map

class UserDataStore(context: Context) {

    private val dataStore = context.createDataStore("MyUserDataStore")

    companion object {
        val USER_NAME_KEY = preferencesKey<String>("USER_NAME")
        val USER_AGE_KEY = preferencesKey<Int>("USER_AGE")
    }

    suspend fun storeUser(name: String, age: Int) {
        dataStore.edit {
            it[USER_NAME_KEY] = name
            it[USER_AGE_KEY] = age
        }
    }

    var userNameFlow = dataStore.data.map { it[USER_NAME_KEY] ?: "" }
    var userAgeFlow = dataStore.data.map { it[USER_AGE_KEY] ?: 0 }
}