package com.example.test2023app.model.response.players_info

import com.example.test2023app.model.response.BaseResponse
import com.google.gson.annotations.SerializedName

data class PlayersInfo (
  @SerializedName("data"   ) var data   : PlayerData?   = PlayerData()
): BaseResponse()