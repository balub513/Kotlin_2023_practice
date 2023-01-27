package com.example.test2023app.model.response.players_info

import com.google.gson.annotations.SerializedName

data class PlayersInfo (

  @SerializedName("apikey" ) var apikey : String? = null,
  @SerializedName("data"   ) var data   : PlayerData?   = PlayerData(),
  @SerializedName("status" ) var status : String? = null,
  @SerializedName("info"   ) var info   : Info?   = Info()

)