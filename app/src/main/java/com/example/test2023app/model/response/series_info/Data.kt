package com.example.test2023app.model.response.series_info

import com.google.gson.annotations.SerializedName


data class Data (
    @SerializedName("info"      ) var info      : Info?                = Info(),
    @SerializedName("matchList" ) var matchList : ArrayList<MatchList> = arrayListOf()

)