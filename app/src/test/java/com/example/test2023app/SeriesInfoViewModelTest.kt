package com.example.test2023app

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.test2023app.model.NetworkResult
import com.example.test2023app.model.response.serieses.Series
import com.example.test2023app.repository.CricRepo
import com.example.test2023app.service.CricService
import com.example.test2023app.utils.TestCoroutineRule
import com.example.test2023app.viewmodel.SeriesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class SeriesInfoViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repo: CricRepo

    @Mock
    private lateinit var service: CricService

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var apiSeriesObserver: Observer<NetworkResult<Response<Series>>>

    @Mock
    private lateinit var resp: Response<Series>

    @Before
    fun setup() {

    }
//
//    @Test
//    fun testSeriesInfoAPISuccess() {
//        testCoroutineRule.runBlockingTest {
//            Mockito.`when`(service.series()).thenReturn(resp)
//            Mockito.`when`(repo.series()).thenReturn(resp)
//            val vm = SeriesViewModel(repo, application)
//            vm.responseSeries.observeForever(apiSeriesObserver)
//            vm.series()
//            Mockito.verify(repo).series()
////            Mockito.verify(apiSeriesObserver).onChanged(NetworkResult.Success(resp))
//            vm.responseSeries.removeObserver(apiSeriesObserver)
//        }
//    }

}