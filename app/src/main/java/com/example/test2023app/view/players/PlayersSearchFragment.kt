package com.example.test2023app.view.players

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.test2023app.R
import com.example.test2023app.databinding.FragmentPlayersSearchBinding


class PlayersSearchFragment : Fragment() {

    private lateinit var binding: FragmentPlayersSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayersSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSearchPlayer.setOnClickListener {
            findNavController().navigate(PlayersSearchFragmentDirections.actionPlayersSearchFragmentToPlayerInfoFragment(101))

        }
    }
}