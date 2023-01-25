package com.example.test2023app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.test2023app.R
import com.example.test2023app.contract.SeriesInfoContract
import com.example.test2023app.model.response.series_info.SeriesInfo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SeriesInfoActivity : AppCompatActivity(), SeriesInfoContract.View {

    companion object {
        private const val TAG: String = "SeriesInfoActivity"
    }

    @Inject
    lateinit var presenter: SeriesInfoContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series_info)

        presenter.getSeriesInfoData()
    }

    override fun successSeriesInfo(seriesInfo: SeriesInfo) {
        Toast.makeText(applicationContext, "SeriesInfo Success !!", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "SeriesInfo --->>  $seriesInfo")

    }

    override fun failureSeriesInfo() {
        Toast.makeText(applicationContext, "SeriesInfo Failed.", Toast.LENGTH_SHORT).show()
    }
}