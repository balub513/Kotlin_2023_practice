package com.example.test2023app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.test2023app.R
import com.example.test2023app.databinding.ActivityDataStoreBinding
import com.example.test2023app.utils.UserDataStore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DataStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataStoreBinding
    private lateinit var dataStore: UserDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataStore = UserDataStore(applicationContext)
        binding.btnSave.setOnClickListener {
            lifecycleScope.launchWhenResumed {
                dataStore.storeUser(
                    binding.etName.text.toString(),
                    binding.etAge.text.toString().toInt()
                )
            }
        }
        observeStoreData()
    }

    private fun observeStoreData() {
        GlobalScope.launch {
            dataStore.userNameFlow.collectLatest {
                binding.tvStoreDataName.text = it
            }
        }
        lifecycleScope.launchWhenResumed {
            dataStore.userAgeFlow.collectLatest{
                binding.tvStoreDataAge.text = it.toString()
            }
        }

    }
}