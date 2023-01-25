package com.example.test2023app.model.response.series_info

import com.google.gson.annotations.SerializedName


data class MatchList (

    @SerializedName("id"             ) var id             : String?             = null,
    @SerializedName("name"           ) var name           : String?             = null,
    @SerializedName("matchType"      ) var matchType      : String?             = null,
    @SerializedName("status"         ) var status         : String?             = null,
    @SerializedName("venue"          ) var venue          : String?             = null,
    @SerializedName("date"           ) var date           : String?             = null,
    @SerializedName("dateTimeGMT"    ) var dateTimeGMT    : String?             = null,
    @SerializedName("teams"          ) var teams          : ArrayList<String>   = arrayListOf(),
    @SerializedName("teamInfo"       ) var teamInfo       : ArrayList<TeamInfo> = arrayListOf(),
    @SerializedName("fantasyEnabled" ) var fantasyEnabled : Boolean?            = null,
    @SerializedName("bbbEnabled"     ) var bbbEnabled     : Boolean?            = null,
    @SerializedName("hasSquad"       ) var hasSquad       : Boolean?            = null,
    @SerializedName("matchStarted"   ) var matchStarted   : Boolean?            = null,
    @SerializedName("matchEnded"     ) var matchEnded     : Boolean?            = null

)