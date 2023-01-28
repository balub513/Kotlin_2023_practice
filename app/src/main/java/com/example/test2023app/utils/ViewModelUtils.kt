package com.example.test2023app.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test2023app.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun ViewModel.safeLaunch(block: suspend CoroutineScope.() -> Unit) {
    this.viewModelScope.launch {
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