package com.example.test2023app.view.players_navigation_jetpack

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.test2023app.databinding.FragmentPlayersSearchNavBinding
import com.example.test2023app.model.response.players_match.Player
import com.example.test2023app.view.adapters.PlayersAdapter
import com.example.test2023app.viewmodel.CricketMatchesViewModel
import com.example.test2023app.viewmodel.PlayersSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersSearchFragmentNAV : Fragment() {

    private lateinit var binding: FragmentPlayersSearchNavBinding
    private val viewModel: PlayersSearchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayersSearchNavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        binding.btnSearchPlayer.setOnClickListener {
            // findNavController().navigate(PlayersSearchFragmentNAVDirections.actionPlayersSearchFragmentToPlayerInfoFragment(101,"Balu","IND"))
            viewModel.playersByName()
        }
    }

    private fun initAdapter() {


        val playersAdapter = PlayersAdapter(ArrayList<Player>(), requireContext())
        binding.rvSearchedPlayers.adapter = playersAdapter

    }
}