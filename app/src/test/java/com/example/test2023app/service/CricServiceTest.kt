package com.example.test2023app.service

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.test2023app.di.module.NetworkModule
import com.example.test2023app.model.response.current_matches.CurrentMatches
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CricServiceTest {

    private lateinit var apiService: CricService

    private lateinit var gson: Gson
    private lateinit var mockWebServer: MockWebServer

    var testDispatcher = StandardTestDispatcher()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)

        mockWebServer = MockWebServer()
        gson = Gson()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(CricService::class.java)
    }

    @Test
    fun `get Matches Api Test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("{}"))
            val currentMatches: Response<CurrentMatches> =
                apiService.currentMatches(NetworkModule.API_KEY)
            val takeRequest = mockWebServer.takeRequest()
            assertEquals(takeRequest.path, "/currentMatches?apikey=${NetworkModule.API_KEY}")
            assertEquals(true, currentMatches.body()?.data != null)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}