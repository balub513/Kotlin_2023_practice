package com.example.test2023app.model.response.cric_score

import com.google.gson.annotations.SerializedName


data class CricScore (

    @SerializedName("apikey" ) var apikey : String?         = null,
    @SerializedName("data"   ) var data   : ArrayList<Data> = arrayListOf(),
    @SerializedName("status" ) var status : String?         = null,
    @SerializedName("info"   ) var info   : Info?           = Info()

)