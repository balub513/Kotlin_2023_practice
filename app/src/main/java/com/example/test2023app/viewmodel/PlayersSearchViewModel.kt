package com.example.test2023app.viewmodel

import android.util.Log
import com.example.blib.safeLaunchFromModule
import com.example.test2023app.di.module.db_module.UserEntity
import com.example.test2023app.model.response.players_match.Player
import com.example.test2023app.repository.CricRepo
import com.example.test2023app.repository.RoomDBRepo
import com.example.test2023app.utils.safeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PlayersSearchViewModel @Inject constructor(
    private val repo: CricRepo,
    private val dbRepository: RoomDBRepo
) : BaseViewModel() {

    companion object {
        private const val TAG: String = "CricketMatchesViewModel"
    }

    val playersStateFlow: MutableStateFlow<ArrayList<Player>> =
        MutableStateFlow(java.util.ArrayList())
    val playersFailedStateFlow = MutableStateFlow<String?>(null)

    val searchStringStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    val clickListenSharedFlow: MutableSharedFlow<String> = MutableSharedFlow(1)

    val resultsCount: MutableStateFlow<Int> =
        MutableStateFlow(0)

    fun playersByName(playerName: String) {
        safeLaunch {
            val response = repo.playersListByName(playerName)
            if (response.isSuccessful) {
                resultsCount.value = response.body()?.data!!.size
                playersStateFlow.value = response.body()?.data!!
            } else
                playersFailedStateFlow.value = "CurrentMatches API failed"
        }
        testDB()
    }

    fun nextClicked() {
        clickListenSharedFlow.tryEmit("Next Button Clicked")
    }


    private fun testDB() {
        safeLaunchFromModule {
            dbRepository.insertUser(UserEntity(name = "BALU", age = "10"))
            val users = dbRepository.fetchUsers()
            Log.d("Users", users.toString())
        }

    }
}