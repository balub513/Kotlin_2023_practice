package com.example.komalkotlin2024.roomdb

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.komalkotlin2024.getOrAwaitValueTest
import io.reactivex.Maybe
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import kotlin.math.log

//@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class LoginEmployeeViewModelTest {


    private lateinit var loginViewModel: LoginEmployeeViewModel

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

//    @get:Rule
//    val testCoroutineRule = TestCoroutineRule()
//
//    @get:Rule
//    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var employeeDao: EmployeeDAO

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitSingleSchedulerHandler { Schedulers.trampoline() }

        MockitoAnnotations.openMocks(this);
        val applicationMock = Mockito.mock(Application::class.java)
        loginViewModel = LoginEmployeeViewModel(applicationMock, employeeDao)
    }

    @Test
    fun testOne(){
        Assert.assertEquals(11,11)
    }

    @Test
    fun getEmployeeTest(){
//        runBlocking {
            val employee = Employee(name = "Princee", password = "Yoshi")
            val response = Maybe.just(employee)

            //`when`(employeeDao.employeeDao()).thenReturn(employeeDAO)
            `when`(employeeDao.getEmployee(anyString(), anyString())).thenReturn(response)
           // loginViewModel.insertEmployee(employee)
            loginViewModel.getEmployee("Princee", "Yoshi")
//            assert(loginViewModel.employee.value?.name.equals("Princee"))
            Assert.assertEquals(loginViewModel.employee.getOrAwaitValueTest(), "Princee")
//        }

    }

    @After
    fun tearDown(){
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }


}