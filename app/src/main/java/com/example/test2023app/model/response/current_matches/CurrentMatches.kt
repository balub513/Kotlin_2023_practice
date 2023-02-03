package com.example.test2023app.model.response.current_matches

import com.example.test2023app.model.response.BaseResponse
import com.google.gson.annotations.SerializedName

class CurrentMatches(
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf()
) : BaseResponse()