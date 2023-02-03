package com.example.test2023app.model.response

import com.example.test2023app.model.response.players_match.Info
import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("apikey") var apikey: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("info") var info: Info? = Info()
)