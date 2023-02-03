package com.example.test2023app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.test2023app.databinding.ActivitySeriesSealedBinding
import com.example.test2023app.model.NetworkResult
import com.example.test2023app.viewmodel.SeriesInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesActivitySealed : AppCompatActivity() {

    private lateinit var viewmodel: SeriesInfoViewModel
    private lateinit var binding: ActivitySeriesSealedBinding


    companion object {
        private const val TAG: String = "CricketMatchesActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeriesSealedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel =
            ViewModelProvider(this)[SeriesInfoViewModel::class.java]
        binding.btnSeriesInfoDataSealed.setOnClickListener { getSeriesInfo() }
        observeResponse()
    }

    private fun observeResponse() {
        viewmodel.responseSeriesInfo.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.tvSeriesInfoSealed.text = response.data?.body().toString()
                }
                is NetworkResult.Loading -> {
                    Toast.makeText(applicationContext, "Loading ..", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Error -> {
                    binding.tvSeriesInfoSealed.text = response.data.toString()
                }
            }
        }

    }

    private fun getSeriesInfo() {
        viewmodel.seriesInfo()
    }
}