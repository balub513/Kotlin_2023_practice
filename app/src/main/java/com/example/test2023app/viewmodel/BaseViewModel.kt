package com.example.test2023app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel constructor(application: Application) : AndroidViewModel(application) {

    var showProgressStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun ViewModel.safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        this.viewModelScope.launch {
            try {
                block()
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun ViewModel.safeLaunchWithLoading(block: suspend CoroutineScope.() -> Unit) {
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

    private fun showProgress() {
        showProgressStateFlow.value = true
    }

    private fun dismissProgress() {
        showProgressStateFlow.value = false
    }
}