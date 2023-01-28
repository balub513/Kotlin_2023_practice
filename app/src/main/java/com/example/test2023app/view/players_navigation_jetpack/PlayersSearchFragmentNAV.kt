package com.example.test2023app.view.players_navigation_jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.test2023app.R
import com.example.test2023app.databinding.FragmentPlayersSearchNavBinding
import com.example.test2023app.utils.safeLaunch
import com.example.test2023app.view.adapters.PlayersAdapter
import com.example.test2023app.viewmodel.PlayersSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersSearchFragmentNAV :
    BaseFragment<FragmentPlayersSearchNavBinding, PlayersSearchViewModel>() {

    private lateinit var playersAdapter: PlayersAdapter

    override fun getViewModel(): PlayersSearchViewModel {
        val viewModel: PlayersSearchViewModel by viewModels()
        return viewModel
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_players_search_nav
    }

    override fun setup() {
        binding.vm = vm
        initAdapter()
        binding.btnSearchPlayer.setOnClickListener {
            // findNavController().navigate(PlayersSearchFragmentNAVDirections.actionPlayersSearchFragmentToPlayerInfoFragment(101,"Balu","IND"))
            binding.etPlayerSearch.text?.let { vm.playersByName(binding.etPlayerSearch.text.toString()) }
        }
    }

    override fun setObservers() {
        safeLaunch {
            vm.playersStateFlow.collect {
                playersAdapter.notifyDataSetChanged(it)
            }
        }
    }

    private fun initAdapter() {
        playersAdapter = PlayersAdapter(ArrayList(), requireContext())
        binding.rvSearchedPlayers.adapter = playersAdapter

    }


}