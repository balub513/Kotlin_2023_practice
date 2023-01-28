package com.example.test2023app.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test2023app.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.safeLaunch(dispatcher : CoroutineDispatcher= Dispatchers.Default,block: suspend CoroutineScope.() -> Unit) {
    this.viewModelScope.launch(dispatcher) {
        try {
            block()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}

fun BaseViewModel.safeLaunchWithLoading(block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch {
        try {
            showProgress()
            block.invoke(this)
        } catch (e: Exception) {
            e.printStackTrace()
            dismissProgress()
        } finally {
            dismissProgress()
        }
    }


}