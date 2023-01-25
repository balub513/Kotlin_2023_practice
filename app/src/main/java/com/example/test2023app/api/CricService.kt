package com.example.test2023app.api

import com.example.test2023app.data.ResponseListUsers
import com.example.test2023app.model.response.countries.Countries
import com.example.test2023app.model.response.current_matches.CurrentMatches
import com.example.test2023app.model.response.players_match.MatchedPlayers
import com.example.test2023app.model.response.series_info.SeriesInfo
import com.example.test2023app.model.response.serieses.Series
import com.example.test2023app.module.NetworkModule
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CricService {

    @GET("/api/users?page=2")
    suspend fun getAllUsers(): Response<ResponseListUsers>

    @GET("currentMatches")
    suspend fun currentMatches(@Query("apikey") apiKey: String = NetworkModule.API_KEY):
            Response<CurrentMatches>

    @GET("series_info")
    suspend fun seriesInfo(@Query("apikey") apiKey: String = NetworkModule.API_KEY): Response<SeriesInfo>

    @GET("series")
    suspend fun series(@Query("apikey") apiKey: String = NetworkModule.API_KEY): Response<Series>

    @GET("players")
    suspend fun playersListByName(
        @Query("apikey") apiKey: String = NetworkModule.API_KEY,
        @Query("search") search: String
    )
            : Response<MatchedPlayers>

    @GET("countries")
    suspend fun countryList(): Response<Countries>

}