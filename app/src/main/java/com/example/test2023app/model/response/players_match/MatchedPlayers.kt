package com.example.test2023app.model.response.players_match

import com.example.test2023app.model.response.BaseResponse
import com.google.gson.annotations.SerializedName


data class MatchedPlayers (
  @SerializedName("data"   ) var data   : ArrayList<Player> = arrayListOf()
): BaseResponse()