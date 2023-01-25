package com.example.test2023app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.test2023app.contract.SeriesInfoContract
import com.example.test2023app.databinding.ActivitySeriesInfoBinding
import com.example.test2023app.model.response.series_info.SeriesInfo
import com.example.test2023app.model.response.serieses.Series
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SeriesActivity : AppCompatActivity(), SeriesInfoContract.View {

    companion object {
        private const val TAG: String = "SeriesInfoActivity"
    }

    private lateinit var binding: ActivitySeriesInfoBinding

    @Inject
    lateinit var presenter: SeriesInfoContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeriesInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSeriesInfoData.setOnClickListener { presenter.getSeries() }
    }

    override fun successSeriesInfo(seriesInfo: Series) {
        Toast.makeText(applicationContext, "SeriesInfo Success !!", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "SeriesInfo --->>  $seriesInfo")
        binding.tvSeriesInfo.text = seriesInfo.toString()

    }

    override fun failureSeriesInfo() {
        Toast.makeText(applicationContext, "SeriesInfo Failed.", Toast.LENGTH_SHORT).show()
        binding.tvSeriesInfo.text = "SeriesInfo Failed."
    }
}