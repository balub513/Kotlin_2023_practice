package com.example.test2023app.model.response.cric_score

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("id"          ) var id          : String? = null,
  @SerializedName("dateTimeGMT" ) var dateTimeGMT : String? = null,
  @SerializedName("matchType"   ) var matchType   : String? = null,
  @SerializedName("status"      ) var status      : String? = null,
  @SerializedName("ms"          ) var ms          : String? = null,
  @SerializedName("t1"          ) var t1          : String? = null,
  @SerializedName("t2"          ) var t2          : String? = null,
  @SerializedName("t1s"         ) var t1s         : String? = null,
  @SerializedName("t2s"         ) var t2s         : String? = null

)