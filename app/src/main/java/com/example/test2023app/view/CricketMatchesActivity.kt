package com.example.test2023app.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.test2023app.databinding.CricketMatchesActivityBinding
import com.example.test2023app.utils.CUtils
import com.example.test2023app.utils.safeLaunchWhenResume
import com.example.test2023app.viewmodel.CricketMatchesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CricketMatchesActivity : AppCompatActivity() {

    private lateinit var viewmodel: CricketMatchesViewModel
    private lateinit var binding: CricketMatchesActivityBinding


    companion object {
        private const val TAG: String = "CricketMatchesActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CricketMatchesActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel =
            ViewModelProvider(this)[CricketMatchesViewModel::class.java]
        binding.btnCurrentMatches.setOnClickListener { getCurrentMatches() }
        observeResponse()
    }

    private fun observeResponse() {
        viewmodel.currentMatches.observe(this) {
            Log.d(TAG, it.toString())
            binding.tvCricketMatches.text = it.toString()
            Toast.makeText(applicationContext, "Current Matches Success: $it", Toast.LENGTH_SHORT)
                .show()
        }
        viewmodel.currentMatchesFailed.observe(this) {
            Log.d(TAG, it.toString())
            binding.tvCricketMatches.text = it.toString()
            Toast.makeText(applicationContext, "Current Api Failed: $it", Toast.LENGTH_SHORT).show()
        }

        safeLaunchWhenResume {
            val msg = viewmodel.navigateToSeriesInfoChannel.receive()
            if (msg == CUtils.CHANNEL_NAVIGATE_TO_SERIES_INFO) {
                Toast.makeText(applicationContext, "Channel Received", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getCurrentMatches() {
        viewmodel.currentMatches()
    }
}