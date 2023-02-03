package com.example.test2023app.model.response.series_info

import com.example.test2023app.model.response.BaseResponse
import com.example.test2023app.model.response.series_info.Data
import com.example.test2023app.model.response.series_info.Info
import com.google.gson.annotations.SerializedName


data class SeriesInfo (
    @SerializedName("data"   ) var data   : Data?   = Data()
): BaseResponse()