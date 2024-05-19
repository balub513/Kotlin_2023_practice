package com.example.komalkotlin2024.mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.komalkotlin2024.mvvm.model.User
import com.example.komalkotlin2024.mvvm.model.UserApiRepository
import com.example.komalkotlin2024.mvvm.model.UserApiViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserApiViewModelTest {

    private lateinit var viewModel: UserApiViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var testDispatcher = StandardTestDispatcher()


    //@Mock
   // private lateinit var userApiRepository: UserApiRepository

    @Mock
    private lateinit var user: User

    @Before
    fun setUP(){
        viewModel = UserApiViewModel()
        Dispatchers.setMain(testDispatcher)

    }

    @Test
    fun getUsersTest(){
        runBlocking {
           // val user = mock(User::class.java)
            //user.name = "komal"
            val response = flowOf(listOf(user))
            `when`(UserApiRepository().getUsers()).thenReturn(response)
            viewModel.getUsers()
            assert(viewModel.usersStateFlow.value.size == 1)
        }

    }
}