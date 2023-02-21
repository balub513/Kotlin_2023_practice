package com.example.test2023app.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.test2023app.di.module.NetworkModule
import com.example.test2023app.model.response.current_matches.CurrentMatches
import com.example.test2023app.model.response.current_matches.Data
import com.example.test2023app.service.CricService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
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
class CricRepoTest {

    private lateinit var cricRepo: CricRepo

    var testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var service: CricService

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        cricRepo = CricRepo(service)
    }

    @Test
    fun testCurrentMatches() {
        runBlocking {
            Mockito.`when`(service.currentMatches(NetworkModule.API_KEY))
                .thenReturn(Response.success(CurrentMatches(arrayListOf(Data(id = "10")))))
            val currentMatches = cricRepo.currentMatches()
            assertEquals(currentMatches.isSuccessful, true)
        }
    }
}