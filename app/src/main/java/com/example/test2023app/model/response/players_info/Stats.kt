package com.example.test2023app.model.response.players_info

import com.google.gson.annotations.SerializedName


data class Stats (

  @SerializedName("fn"        ) var fn        : String? = null,
  @SerializedName("matchtype" ) var matchtype : String? = null,
  @SerializedName("stat"      ) var stat      : String? = null,
  @SerializedName("value"     ) var value     : String? = null

)