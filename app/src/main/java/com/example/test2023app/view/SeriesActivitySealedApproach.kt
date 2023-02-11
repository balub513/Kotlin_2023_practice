package com.example.test2023app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.test2023app.databinding.ActivitySeriesSealedBinding
import com.example.test2023app.model.NetworkResult
import com.example.test2023app.utils.safeLaunchWhenResume
import com.example.test2023app.viewmodel.SeriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SeriesActivitySealed : AppCompatActivity() {

    private lateinit var viewmodel: SeriesViewModel
    private lateinit var binding: ActivitySeriesSealedBinding


    companion object {
        private const val TAG: String = "CricketMatchesActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeriesSealedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel =
            ViewModelProvider(this)[SeriesViewModel::class.java]
        binding.btnSeriesInfoDataSealed.setOnClickListener { getSeriesInfo() }
        observeResponse()
    }

    private fun observeResponse() {
        lifecycleScope.launchWhenStarted {
            viewmodel.responseSeries.collect{response ->
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
                    else -> {}
                }
            }
        }
    }

    private fun getSeriesInfo() {
        viewmodel.series()
    }
}