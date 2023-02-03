package com.example.test2023app.model.response.cric_score

import com.example.test2023app.model.response.BaseResponse
import com.google.gson.annotations.SerializedName


data class CricScore (
    @SerializedName("data"   ) var data   : ArrayList<Data> = arrayListOf()
) : BaseResponse()