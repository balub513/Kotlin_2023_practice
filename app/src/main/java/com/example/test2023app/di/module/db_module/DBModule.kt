package com.example.test2023app.di.module.db_module

import android.content.Context
import com.example.test2023app.repository.RoomDBRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DBModule {

    @Provides
    fun provideUserDao(@ApplicationContext context: Context): UserDao {
        return UserDatabase.getInstance(context).userDao()
    }

    @Provides
    fun provideRoomDBRepo(userDao: UserDao): RoomDBRepo {
        return RoomDBRepo(userDao)
    }
}