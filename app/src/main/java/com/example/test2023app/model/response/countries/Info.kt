package com.example.test2023app.model.response.countries

import com.google.gson.annotations.SerializedName


data class Info (

  @SerializedName("hitsToday"  ) var hitsToday  : Int?    = null,
  @SerializedName("hitsUsed"   ) var hitsUsed   : Int?    = null,
  @SerializedName("hitsLimit"  ) var hitsLimit  : Int?    = null,
  @SerializedName("credits"    ) var credits    : Int?    = null,
  @SerializedName("server"     ) var server     : Int?    = null,
  @SerializedName("offsetRows" ) var offsetRows : Int?    = null,
  @SerializedName("totalRows"  ) var totalRows  : Int?    = null,
  @SerializedName("queryTime"  ) var queryTime  : Double? = null,
  @SerializedName("s"          ) var s          : Int?    = null,
  @SerializedName("cache"      ) var cache      : Int?    = null

)