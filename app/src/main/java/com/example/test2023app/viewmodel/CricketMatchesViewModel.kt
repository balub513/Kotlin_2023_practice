package com.example.test2023app.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.test2023app.model.response.current_matches.CurrentMatches
import com.example.test2023app.repository.CricRepo
import com.example.test2023app.utils.safeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CricketMatchesViewModel @Inject constructor(
    private val repo: CricRepo
) : BaseViewModel() {

    companion object {
        private const val TAG: String = "CricketMatchesViewModel"
    }

    private val _currentMatches = MutableLiveData<CurrentMatches>()
    val currentMatches: LiveData<CurrentMatches> = _currentMatches

    private val _currentMatchesFailed = MutableLiveData<String>()
    val currentMatchesFailed: LiveData<String> = _currentMatchesFailed


    fun currentMatches() {
        safeLaunch {
            val currentMatches = repo.currentMatches()
            if (currentMatches.isSuccessful)
                _currentMatches.postValue(currentMatches.body())
            else
                _currentMatchesFailed.postValue("CurrentMatches API failed")
        }
    }
}


//fun currentMatches() {
//        viewModelScope.launch {
//            try {
//                val currentMatches = repo.currentMatches()
//                if (currentMatches.isSuccessful)
//                    _currentMatches.value = currentMatches.body()
//                else
//                    _currentMatchesFailed.value = "CurrentMatches API failed"
//            } catch (e: Exception) {
//                _currentMatchesFailed.value = e.message
//                e.printStackTrace()
//            }
//        }
//}
//fun ViewModel.concate(a: Int, b: Int, result: (String)-> Unit) {
//    val s = (a+b).toString()
//
//    return result(s)
//}
//
//
//concate(1, 2) { result ->
//
//}
//concate(1, 2, { result ->
//
//})