package com.example.test2023app.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test2023app.databinding.LoadStateViewBinding

/*
We basically bind the progress bar from load_state_view.xml to the LoadStateViewHolder class.
The key thing to notice here is the progress bar is visible only if loadState is LoadState.Loading.

The onBindViewHolder() inside MainLoadStateAdapter is called every time the RecyclerView is invalidated,
 which provides the possibility to show and hide the progress bar based on the load state.
 */
class MainLoadStateAdapter : LoadStateAdapter<MainLoadStateAdapter.LoadSateViewHolder>() {

    override fun onBindViewHolder(holder: LoadSateViewHolder, loadState: LoadState) {
        holder.binding.progress.isVisible = loadState is LoadState.Loading
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadSateViewHolder {
        return LoadSateViewHolder(
            LoadStateViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    class LoadSateViewHolder(val binding: LoadStateViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}