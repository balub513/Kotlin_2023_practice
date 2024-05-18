package com.example.test2023app

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
//import com.android.example.livedatabuilder.FakeDataSource
import com.example.test2023app.FirstWay.TestCoroutineRule
import com.example.test2023app.model.NetworkResult
import com.example.test2023app.model.response.serieses.Series
import com.example.test2023app.model.response.serieses.SeriesInfo
import com.example.test2023app.repository.CricRepo
import com.example.test2023app.service.CricService
import com.example.test2023app.SecondWay.MainCoroutineRule
import com.example.test2023app.viewmodel.SeriesViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.yield
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TodoViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {

    }


}