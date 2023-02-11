package com.example.test2023app.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test2023app.databinding.ActivityMainBinding
import com.example.test2023app.view.players_navigation_jetpack.PlayersActivityNAV

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFlowOpearators.setOnClickListener{
            startActivity(Intent(this, FlowOperatorsActivity::class.java))
        }
        binding.btnCurrentMatches.setOnClickListener {
            startActivity(Intent(this, CricketMatchesActivity::class.java))
        }
        binding.btnSeriesInfo.setOnClickListener {
            startActivity(Intent(this, SeriesActivity::class.java))
        }
        binding.btnPlayers.setOnClickListener {
            startActivity(Intent(this, PlayersActivityNAV::class.java))

//            Navigation deeplink test
//            val intent = Intent()
//            intent.data = Uri.parse("app://player/200?name=BBK&country=INDIA")
            startActivity(intent)
        }
        binding.btnDataStore.setOnClickListener {
            startActivity(Intent(this, DataStoreActivity::class.java))
        }

        binding.btnSeriesInfoSealed.setOnClickListener {
            startActivity(Intent(this, SeriesActivitySealed::class.java))
        }
        binding.btnPager3.setOnClickListener {
            startActivity(Intent(this, Pager3Activity::class.java))
        }

    }
}