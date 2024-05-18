package com.example.test2023app.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.example.test2023app.di.module.db_module.UserEntity
import com.example.test2023app.getOrAwaitValueTest
import com.example.test2023app.repository.CricRepo
import com.example.test2023app.repository.RoomDBRepo
import io.reactivex.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RoomViewModelTest {

    private lateinit var roomViewModel: RoomViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var repo : CricRepo

    @Mock
    lateinit var dbRepo: RoomDBRepo

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        roomViewModel = RoomViewModel(repo, dbRepo)
    }

    @Test
    fun testGetUsersSuccess(){
        runBlocking {
            Mockito.`when`(dbRepo.fetchUsers()).thenReturn(listOf(UserEntity(name = "balu", age = "10")))
            roomViewModel.getUsersFromDB()
            val userEntityList = roomViewModel.userEntitiesLiveData.getOrAwaitValueTest()
            assertEquals(userEntityList[0].name, "balu")

        }

    }


}