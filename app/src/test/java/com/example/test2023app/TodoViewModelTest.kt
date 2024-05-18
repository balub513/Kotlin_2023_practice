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
import com.example.test2023app.withoutDI.repo.TodoDao
import com.example.test2023app.withoutDI.repo.TodoRepository
import com.example.test2023app.withoutDI.vm.TodoViewModel
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
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class TodoViewModelTest {

    private lateinit var todoViewModel: TodoViewModel

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var todoDao: TodoDao

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);
        var repo = TodoRepository(todoDao)
        val applicationMock = Mockito.mock(Application::class.java)
        todoViewModel = TodoViewModel(applicationMock, repo)
    }

    @Test
    fun testGetName(){
        val name = todoViewModel.getName()
        Assert.assertEquals(name,"BALU")
    }


}