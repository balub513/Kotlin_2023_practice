package com.example.test2023app.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.test2023app.di.module.NetworkModule
import com.example.test2023app.getOrAwaitValueTest
import com.example.test2023app.model.response.current_matches.CurrentMatches
import com.example.test2023app.model.response.current_matches.Data
import com.example.test2023app.repository.CricRepo
import com.example.test2023app.service.CricService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CricketMatchesViewModelTest {

    private lateinit var cricketMatchesViewModel: CricketMatchesViewModel

    private lateinit var cricRepo: CricRepo

    private var testDispatcher = StandardTestDispatcher()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var service: CricService

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        cricRepo = CricRepo(service)
        cricketMatchesViewModel = CricketMatchesViewModel(cricRepo)
    }

    @Test
    fun currentMatchesTest() {
        runBlocking {
            Mockito.`when`(service.currentMatches(NetworkModule.API_KEY))
                .thenReturn(Response.success(CurrentMatches(arrayListOf(Data(), Data()))))
            cricketMatchesViewModel.currentMatches()
            val currentMatches = cricketMatchesViewModel.currentMatches.getOrAwaitValueTest()
            assertEquals(currentMatches.data.size, 2)
        }

    }


}