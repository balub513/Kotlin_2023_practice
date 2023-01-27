package com.example.test2023app.model.response.players_match

import com.google.gson.annotations.SerializedName


data class MatchedPlayers (

  @SerializedName("apikey" ) var apikey : String?         = null,
  @SerializedName("data"   ) var data   : ArrayList<Player> = arrayListOf(),
  @SerializedName("status" ) var status : String?         = null,
  @SerializedName("info"   ) var info   : Info?           = Info()

)