package com.example.test2023app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.test2023app.databinding.ActivityFlowOperatorsBinding
import com.example.test2023app.viewmodel.FlowOperatorsViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.concurrent.Task
import kotlin.coroutines.suspendCoroutine

@AndroidEntryPoint
class FlowOperatorsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlowOperatorsBinding
    private lateinit var viewModel: FlowOperatorsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowOperatorsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =
            ViewModelProvider(this)[FlowOperatorsViewModel::class.java]
        binding.btnAyncTwoAPICalls.setOnClickListener{
            viewModel.playersAndPlayersInfoParallelCall()
        }
        binding.btnWithContextTwoAPICalls.setOnClickListener{
            viewModel.playersAndPlayersInfoParallelCall2()
        }
    }
}