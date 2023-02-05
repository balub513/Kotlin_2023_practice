package com.example.test2023app.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class MainViewModel(val dao: ItemDao) : ViewModel() {
    val data = Pager(PagingConfig(pageSize = 20, enablePlaceholders = false, initialLoadSize = 20)) {
        MainPagingSource(dao)
    }.flow.cachedIn(viewModelScope)
}

class MainViewModelFactory(val dao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao = dao) as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}