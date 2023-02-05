package com.example.test2023app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.test2023app.R
import com.example.test2023app.databinding.ActivityPager3Binding
import com.example.test2023app.paging.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class Pager3Activity : AppCompatActivity() {


    private lateinit var dao: ItemDao
    private lateinit var binding: ActivityPager3Binding
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(dao) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPager3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = ItemDatabase.getInstance(applicationContext).itemDao()

        val adapter = MainAdapter()
        binding.recyclerView.adapter = adapter.withLoadStateFooter(MainLoadStateAdapter())

        lifecycleScope.launch {
            viewModel.data.collectLatest { adapter.submitData(it) }
        }

    }
}