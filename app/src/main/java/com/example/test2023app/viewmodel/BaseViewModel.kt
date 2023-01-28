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


    fun showProgress() {
        showProgressStateFlow.value = true
    }

    fun dismissProgress() {
        showProgressStateFlow.value = false
    }
}