package com.example.test2023app.model.response.players_info

import com.google.gson.annotations.SerializedName


data class PlayerData (

  @SerializedName("id"           ) var id           : String?          = null,
  @SerializedName("name"         ) var name         : String?          = null,
  @SerializedName("dateOfBirth"  ) var dateOfBirth  : String?          = null,
  @SerializedName("role"         ) var role         : String?          = null,
  @SerializedName("battingStyle" ) var battingStyle : String?          = null,
  @SerializedName("bowlingStyle" ) var bowlingStyle : String?          = null,
  @SerializedName("placeOfBirth" ) var placeOfBirth : String?          = null,
  @SerializedName("country"      ) var country      : String?          = null,
  @SerializedName("playerImg"    ) var playerImg    : String?          = null,
  @SerializedName("stats"        ) var stats        : ArrayList<Stats> = arrayListOf()

)