package com.example.test2023app.view.players_navigation_jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test2023app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersActivityNAV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_nav)
    }
}