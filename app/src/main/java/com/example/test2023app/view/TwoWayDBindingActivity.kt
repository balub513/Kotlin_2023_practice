package com.example.test2023app.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import com.example.test2023app.databinding.ActivityTwoWayDbindingBinding
import com.example.test2023app.model.Gender
import com.example.test2023app.model.Nation
import com.example.test2023app.viewmodel.TwoWayDBindingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TwoWayDBindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTwoWayDbindingBinding

    private lateinit var viewModel: TwoWayDBindingViewModel


    private val TAG = "TwoWayDBindingActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwoWayDbindingBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[TwoWayDBindingViewModel::class.java]
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.vm1 = viewModel
        binding.register.setOnClickListener { getAllValues() }
        attachObservers()
    }

    private fun getAllValues() {
        lifecycleScope.launch {
            val userName: String = viewModel.userNameFlow.value
            val email: String = viewModel.emailFlow1.value
            val password: String = viewModel.passwordFlow.value
            val nationality: Nation = viewModel.nationalityFlow.value
            val gender: Gender = viewModel.genderFlow.value

            Log.d("$TAG:", userName)
            Log.d("$TAG:", email)
            Log.d("$TAG:", password)
            Log.d("$TAG:", nationality.toString())
            Log.d("$TAG:", gender.toString())
        }
    }

    @SuppressLint("LongLogTag")
    private fun attachObservers() {
        lifecycleScope.launchWhenResumed {

            viewModel.userNameFlow.collectLatest {
                Log.d("$TAG userNameFlow", it)
            }
        }
        lifecycleScope.launch {
            viewModel.emailFlow1.collectLatest {
                Log.d("$TAG emailFlow", it)
            }
        }
        lifecycleScope.launch {
            viewModel.passwordFlow.collectLatest {
                Log.d("$TAG passwordFlow", it)
            }
        }
        lifecycleScope.launch {
            viewModel.nationalityFlow.collectLatest {
                Log.d("$TAG nationalityFlow", it.toString())
            }
        }
        lifecycleScope.launch {
            viewModel.genderFlow.collectLatest {
                Log.d("$TAG genderFlow", it.toString())
            }
        }

        // for single flow to collect and bind with life cycle we can use 'flowWithLifecycle'
        lifecycleScope.launch {
            viewModel.emailFlow1
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    Log.d("$TAG emailFlow", it.toString())
                }
        }

        // for multiple flows to collect and bind with life cycle we can use 'repeatOnLifecycle' and it is suspend function
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.emailFlow1.collectLatest {

                }
                viewModel.genderFlow.collectLatest {

                }
            }
        }

    }
}