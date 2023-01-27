package com.example.test2023app.mvp.contract

import com.example.test2023app.model.response.series_info.SeriesInfo
import com.example.test2023app.model.response.serieses.Series

interface SeriesInfoContract {

    interface View {
        fun successSeriesInfo(series: Series)
        fun failureSeriesInfo()
    }

    interface Presenter {
        fun getSeries()
    }
}