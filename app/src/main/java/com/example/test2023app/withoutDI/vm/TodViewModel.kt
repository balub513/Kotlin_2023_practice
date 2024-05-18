package com.example.test2023app.withoutDI.vm

import android.app.Application
import androidx.lifecycle.*
import com.example.test2023app.withoutDI.repo.Todo
import com.example.test2023app.withoutDI.repo.TodoDatabase
import com.example.test2023app.withoutDI.repo.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application, var repository: TodoRepository): AndroidViewModel(application) {
//    private val repository: TodoRepository
//    val allTodo : LiveData<List<Todo>>

    init {
//        val dao = TodoDatabase.getDatabase(application).getTodoDao()
//        repository = TodoRepository(dao)
//        allTodo = repository.allTodos
    }

    fun insertTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(todo)
    }

    fun updateTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO){
        repository.update(todo)
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(todo)
    }

    fun getName() = "BALU"
}