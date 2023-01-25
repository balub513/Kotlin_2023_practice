package com.example.test2023app.model.response.series_info

import com.google.gson.annotations.SerializedName


data class TeamInfo (

  @SerializedName("name"      ) var name      : String? = null,
  @SerializedName("shortname" ) var shortname : String? = null,
  @SerializedName("img"       ) var img       : String? = null

)