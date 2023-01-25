package com.example.test2023app.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.test2023app.model.response.current_matches.CurrentMatches
import com.example.test2023app.repo.CricRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CricketMatchesViewModel @Inject constructor(
    private val repo: CricRepo,
    application: Application
) : AndroidViewModel(application) {

    companion object {
        private const val TAG: String = "CricketMatchesViewModel"
    }

    private val _currentMatches = MutableLiveData<CurrentMatches>()
    val currentMatches: LiveData<CurrentMatches> = _currentMatches

    private val _currentMatchesFailed = MutableLiveData<String>()
    val currentMatchesFailed: LiveData<String> = _currentMatchesFailed


    fun currentMatches() {
        viewModelScope.launch {
            try {
                val currentMatches = repo.currentMatches()
                if (currentMatches.isSuccessful)
                    _currentMatches.value = currentMatches.body()
                else
                    _currentMatchesFailed.value = "CurrentMatches API failed"
            } catch (e: Exception) {
                _currentMatchesFailed.value = e.message
                e.printStackTrace()
            }
        }


    }
}