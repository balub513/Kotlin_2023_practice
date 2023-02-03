package com.example.test2023app.model.response.serieses

import com.example.test2023app.model.response.BaseResponse
import com.google.gson.annotations.SerializedName


data class Series(
  @SerializedName("data") var data: ArrayList<SeriesInfo> = arrayListOf(),
) : BaseResponse()