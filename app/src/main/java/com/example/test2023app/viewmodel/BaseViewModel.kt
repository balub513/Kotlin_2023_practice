package com.example.test2023app.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel : ViewModel() {

    var showProgressStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)


    fun showProgress() {
        showProgressStateFlow.value = true
    }

    fun dismissProgress() {
        showProgressStateFlow.value = false
    }
}