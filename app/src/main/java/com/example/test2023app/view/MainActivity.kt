package com.example.test2023app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test2023app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCurrentMatches.setOnClickListener {
            startActivity(Intent(this, CricketMatchesActivity::class.java))
        }
        binding.btnSeriesInfo.setOnClickListener {
            startActivity(Intent(this, SeriesActivity::class.java))
        }
    }
}