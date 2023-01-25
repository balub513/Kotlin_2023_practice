package com.example.test2023app.repo

import com.example.test2023app.api.CricService
import java.lang.reflect.Constructor
import javax.inject.Inject

class CricRepo @Inject constructor(private val service: CricService) {

    suspend fun currentMatches() = service.currentMatches()
    suspend fun seriesInfo() = service.seriesInfo()
}