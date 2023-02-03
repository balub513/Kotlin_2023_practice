package com.example.test2023app.model.response.countries

import com.example.test2023app.model.response.BaseResponse
import com.google.gson.annotations.SerializedName


data class Countries (
    @SerializedName("data"   ) var data   : ArrayList<Data> = arrayListOf()
): BaseResponse()