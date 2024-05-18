package com.example.test2023app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test2023app.R
import com.example.test2023app.databinding.ActivityRoomBinding
import com.example.test2023app.di.module.db_module.UserEntity
import com.example.test2023app.view.adapters.UserEntityAdapter
import com.example.test2023app.viewmodel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RoomActivity : AppCompatActivity() {
    private val TAG  = "RoomActivity"
    private lateinit var userEntityAdapter: UserEntityAdapter
    private lateinit var recyclerView: RecyclerView
    var listUsers: MutableList<UserEntity> = mutableListOf()
    private lateinit var binding: ActivityRoomBinding

    lateinit var viewModel: RoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RoomViewModel::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.AddInDB.setOnClickListener { addUserInDB() }

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        userEntityAdapter = UserEntityAdapter(listUsers)
        recyclerView.adapter = userEntityAdapter

        attachObservers()

    }

    private fun addUserInDB() {
        lifecycleScope.launchWhenResumed {
            val name = viewModel.nameStateFlow.value
            val age = viewModel.ageFlow.value
            viewModel.addUserInDB(UserEntity(name = name!!, age = age!!))
            viewModel?.getUsersFromDB()
        }
    }

    private fun attachObservers() {
        binding.viewModel?.userEntitiesLiveData?.observe(this){
            Log.d(TAG, "list records: $it")
            listUsers.clear()
            listUsers.addAll(it)
            userEntityAdapter.notifyDataSetChanged()
        }

    }
}