package com.example.test2023app.contract

import com.example.test2023app.model.response.series_info.SeriesInfo

interface SeriesInfoContract {

    interface View {
        fun successSeriesInfo(seriesInfo: SeriesInfo)
        fun failureSeriesInfo()
    }

    interface Presenter {
        fun getSeriesInfoData()
    }
}