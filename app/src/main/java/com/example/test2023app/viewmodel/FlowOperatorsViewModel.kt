package com.example.test2023app.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.test2023app.model.response.players_info.PlayersInfo
import com.example.test2023app.model.response.players_match.MatchedPlayers
import com.example.test2023app.repository.CricRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FlowOperatorsViewModel @Inject constructor(application: Application, var repo: CricRepo) :
    AndroidViewModel(application) {

    private lateinit var playerInfoResponse: Response<PlayersInfo>
    private lateinit var matchedPlayersResponse: Response<MatchedPlayers>

    // Both calls executed simultaniously
    fun playersAndPlayersInfoParallelCall() {
        Log.d("FlowOperatorsViewModel", "playersAndPlayersInfoParallelCall")
        viewModelScope.launch {
            val resp1 = async(Dispatchers.IO) {
                Thread.sleep(10000)
                repo.playersListByName("Gayle")
            }
            val resp2 = async(Dispatchers.IO) {
                repo.playerInfo(matchedPlayersResponse.body()?.data?.get(0)?.id!!)
            }
            matchedPlayersResponse = resp1.await()
            playerInfoResponse = resp2.await()
            handleResponses(matchedPlayersResponse, playerInfoResponse)
        }
    }

    // Calls happend sequentailly, one after other
    fun playersAndPlayersInfoParallelCall2() {
        Log.d("FlowOperatorsViewModel", "playersAndPlayersInfoParallelCall")
        viewModelScope.launch {
            val resp1 = withContext(Dispatchers.IO) {
                Thread.sleep(6000)
                repo.playersListByName("Gayle")
            }
            val resp2 = withContext(Dispatchers.IO) {
                repo.playerInfo(playerId = resp1.body()?.data?.get(0)?.id!!);
            }
            handleResponse2(resp1, resp2)
        }
    }

    private fun handleResponse2(resp1: Response<MatchedPlayers>, resp2: Response<PlayersInfo>) {
        Log.d("resp1", resp1.body().toString())
        Log.d("resp2", resp2.body().toString())
    }

    private fun handleResponses(resp1: Response<MatchedPlayers>, resp2: Response<PlayersInfo>) {
        Log.d("matchedPlayersResponse", resp1.body().toString())
        Log.d("playerInfo", resp2.body().toString())
    }
}