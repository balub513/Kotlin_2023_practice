package com.example.komalkotlin2024.roomdb

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.Maybe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    private lateinit var database: EmployeeDatabase
    private lateinit var viewModel: LoginViewModel

    @Mock
    private lateinit var application: Application


    private lateinit var employeeDAO: EmployeeDAO

    @Mock
    private lateinit var roomDatabase: EmployeeDatabase


    @Before
    fun before(){
        MockitoAnnotations.initMocks(this)
        viewModel = LoginViewModel(ApplicationProvider.getApplicationContext())
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            EmployeeDatabase::class.java
        ).allowMainThreadQueries().build()

        employeeDAO = database.employeeDao()
    }

    @Test
    fun getEmployeeTest(){
        runBlocking {
            val employee = Employee(name = "Princee", password = "Yoshi")
            val response = Maybe.just(employee)
            //`when`(database.employeeDao()).thenReturn(employeeDAO)
            //`when`(employeeDAO.getEmployee(anyString(), anyString())).thenReturn(response)
            viewModel.insertEmployee(employee)
            viewModel.getEmployee("Princee", "Yoshi")
            assert(viewModel.employee.value?.name.equals("Princee"))
        }

    }

    @After
    fun tearDown(){

        database.close()
    }
}