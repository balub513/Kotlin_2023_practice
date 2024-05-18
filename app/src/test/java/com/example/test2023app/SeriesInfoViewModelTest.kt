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
class SeriesInfoViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

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

    @Test
    fun testSeriesInfoAPISuccess1() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(service.series1()).thenReturn(getMockSuccessResponse())
            Mockito.`when`(repo.series1()).thenReturn(getMockSuccessResponse())
            val vm = SeriesViewModel(repo)
            vm.series1()
            assertEquals(vm.seriesLiveData.getOrAwaitValue()?.data?.data?.size, 10)
        }
    }

    @Test
    fun testSeriesApiSuccess2() {
        runTest {
            Mockito.`when`(service.series1()).thenReturn(getMockSuccessResponse())
            Mockito.`when`(repo.series1()).thenReturn(getMockSuccessResponse())
            val vm = SeriesViewModel(repo)
            vm.series1()
            assertEquals(vm.seriesLiveData.getOrAwaitValue {  }?.data?.data?.size, 10)
        }
    }

    private fun getMockSuccessResponse(): Series {
        val series = Series()
        var list = ArrayList<SeriesInfo>()
        for (i in 1..10) {
            val seriesInfo = SeriesInfo()
            list.add(seriesInfo)
        }
        series.data = list
        return series
    }

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