package com.example.blib


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.safeLaunchFromModule(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend CoroutineScope.() -> Unit
) {
    this.viewModelScope.launch(dispatcher) {
        try {
            block()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}