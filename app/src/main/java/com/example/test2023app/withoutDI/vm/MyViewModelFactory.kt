package com.example.test2023app.withoutDI.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test2023app.withoutDI.repo.TodoRepository

class MyViewModelFactory constructor(val application: Application, private val repository: TodoRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TodoViewModel::class.java!!)) {
            TodoViewModel(application, this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}