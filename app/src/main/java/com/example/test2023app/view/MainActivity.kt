package com.example.test2023app.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.test2023app.databinding.ActivityMainBinding
import com.example.test2023app.firebase.FireBaseActivity
import com.example.test2023app.view.players_navigation_jetpack.PlayersActivityNAV
import com.example.test2023app.withoutDI.repo.TodoDatabase
import com.example.test2023app.withoutDI.repo.TodoRepository
import com.example.test2023app.withoutDI.vm.MyViewModelFactory
import com.example.test2023app.withoutDI.vm.TodoViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var repo  = TodoRepository(TodoDatabase.getDatabase(application).getTodoDao())
        viewModel = ViewModelProviders.of(this, MyViewModelFactory(application,repo))[TodoViewModel::class.java]

        binding.btnFlowOpearators.setOnClickListener{
            startActivity(Intent(this, FlowOperatorsActivity::class.java))

            val name = viewModel.getName()
            Toast.makeText(this,"$name from VM",Toast.LENGTH_LONG).show()
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
        binding.btnConstrainlayout.setOnClickListener {
            startActivity(Intent(this, ConstrainLayoutActivity::class.java))
        }
        binding.btnTwoWayDatabinding.setOnClickListener {
            startActivity(Intent(this, TwoWayDBindingActivity::class.java))
        }
        binding.btnRoomDbTwoWayDatabinding.setOnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        }
        binding.btnFirebase.setOnClickListener {
            startActivity(Intent(this, FireBaseActivity::class.java))
        }

    }
}