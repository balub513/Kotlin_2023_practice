package com.example.test2023app.view.players_navigation_jetpack


import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test2023app.R
import com.example.test2023app.databinding.FragmentPlayersSearchNavBinding
import com.example.test2023app.utils.safeLaunch
import com.example.test2023app.utils.safeLaunchWhenResume
import com.example.test2023app.view.adapters.PlayersAdapter
import com.example.test2023app.viewmodel.PlayersSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*

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
             findNavController().navigate(PlayersSearchFragmentNAVDirections.actionPlayersSearchFragmentToPlayerInfoFragment(101,"Balu","IND"))
            binding.etPlayerSearch.text?.let { vm.playersByName(binding.etPlayerSearch.text.toString()) }
        }

        safeLaunchWhenResume {
            vm.searchStringStateFlow
                .debounce(300)
                .collect {
                    vm.playersByName(it)
                }
        }


    }

    override fun setObservers() {
        safeLaunchWhenResume {
            vm.playersStateFlow.collect {
                playersAdapter.notifyDataSetChanged(it)
            }
        }
        safeLaunchWhenResume {
            vm.clickListenSharedFlow.collect {
                Toast.makeText(context, "shared flow Next click listened", Toast.LENGTH_SHORT)
                    .show()
                //todo Navigate to Next screen
            }
        }
    }

    private fun initAdapter() {
        playersAdapter = PlayersAdapter(ArrayList(), requireContext())
        binding.rvSearchedPlayers.adapter = playersAdapter


    }


}