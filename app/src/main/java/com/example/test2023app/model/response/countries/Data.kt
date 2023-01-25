package com.example.test2023app.model.response.countries

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("id"          ) var id          : String? = null,
  @SerializedName("name"        ) var name        : String? = null,
  @SerializedName("genericFlag" ) var genericFlag : String? = null

)