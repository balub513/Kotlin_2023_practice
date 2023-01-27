package com.example.test2023app.viewmodel

import android.app.Application
import com.example.test2023app.model.response.players_match.Player
import com.example.test2023app.repository.CricRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PlayersSearchViewModel @Inject constructor(
    private val repo: CricRepo,
    application: Application
) : BaseViewModel(application) {

    companion object {
        private const val TAG: String = "CricketMatchesViewModel"
    }

    private val playersStateFlow: MutableStateFlow<ArrayList<Player>> =
        MutableStateFlow(java.util.ArrayList())
    private val playersFailedStateFlow = MutableStateFlow<String?>(null)


    fun playersByName() {
        safeLaunch {
            val response = repo.playersListByName("Gayle")
            if (response.isSuccessful) {
                playersStateFlow.value = response.body()?.data!!
            } else
                playersFailedStateFlow.value = "CurrentMatches API failed"
        }
    }
}