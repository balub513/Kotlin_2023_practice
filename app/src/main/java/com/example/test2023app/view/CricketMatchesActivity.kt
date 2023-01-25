package com.example.test2023app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.test2023app.databinding.CricketMatchesActivityBinding
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
            ViewModelProvider(this).get<CricketMatchesViewModel>(CricketMatchesViewModel::class.java)
        binding.btnCurrentMatches.setOnClickListener { getCurrentMatches() }
        observeResponse()
    }

    private fun observeResponse() {
        viewmodel.currentMatches.observe(this) {
            Log.d(TAG, it.toString())
        }
        viewmodel.currentMatchesFailed.observe(this){
            Log.d(TAG, it.toString())
        }

    }

    private fun getCurrentMatches() {
        viewmodel.currentMatches()
    }
}