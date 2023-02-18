package com.example.test2023app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import com.example.test2023app.R
import com.example.test2023app.databinding.ActivityTwoWayDbindingBinding
import com.example.test2023app.viewmodel.Gender
import com.example.test2023app.viewmodel.Nation
import com.example.test2023app.viewmodel.TwoWayDBindingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TwoWayDBindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTwoWayDbindingBinding

    private lateinit var viewModel: TwoWayDBindingViewModel


    private val TAG = "TwoWayDBindingActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwoWayDbindingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[TwoWayDBindingViewModel::class.java]
//        binding.register.setOnClickListener { attachObservers() }
        attachObservers()
    }

    private fun attachObservers() {
        lifecycleScope.launchWhenResumed {
            val userName: String = viewModel.userNameFlow.value
            val email: String = viewModel.emailFlow.value
            val password: String = viewModel.passwordFlow.value
            val nationality: Nation = viewModel.nationalityFlow.value
            val gender: Gender = viewModel.genderFlow.value

            viewModel.userNameFlow.collectLatest {
                Log.d(TAG, it)
            }

            Log.d(TAG, userName)
            Log.d(TAG, email)
            Log.d(TAG, password)
            Log.d(TAG, nationality.toString())
            Log.d(TAG, gender.toString())

        }
    }
}