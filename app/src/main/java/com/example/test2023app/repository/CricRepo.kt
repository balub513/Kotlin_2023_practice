package com.example.test2023app.repository

import com.example.test2023app.service.CricService
import javax.inject.Inject

open class CricRepo @Inject constructor(private val service: CricService) {

    suspend fun currentMatches() = service.currentMatches()
    suspend fun series() = service.series()
    suspend fun seriesInfo() = service.seriesInfo()
    suspend fun playersListByName(playerName : String) = service.playersListByName(search = playerName)

    suspend fun playerInfo(playerId: String) = service.playerInfo(playerId = playerId)
}