package com.example.test2023app.model.response.serieses

import com.google.gson.annotations.SerializedName


data class SeriesInfo (

  @SerializedName("id"        ) var id        : String? = null,
  @SerializedName("name"      ) var name      : String? = null,
  @SerializedName("startDate" ) var startDate : String? = null,
  @SerializedName("endDate"   ) var endDate   : String? = null,
  @SerializedName("odi"       ) var odi       : Int?    = null,
  @SerializedName("t20"       ) var t20       : Int?    = null,
  @SerializedName("test"      ) var test      : Int?    = null,
  @SerializedName("squads"    ) var squads    : Int?    = null,
  @SerializedName("matches"   ) var matches   : Int?    = null

)