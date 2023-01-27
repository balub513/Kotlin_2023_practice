package com.example.test2023app.model.response.players_match

import com.google.gson.annotations.SerializedName


data class Player(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("country") var country: String? = null
)