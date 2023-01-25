package com.example.test2023app.model.response.current_matches

import com.google.gson.annotations.SerializedName


data class Score (

  @SerializedName("r"      ) var r      : Int?    = null,
  @SerializedName("w"      ) var w      : Int?    = null,
  @SerializedName("o"      ) var o      : Double?    = null,
  @SerializedName("inning" ) var inning : String? = null

)