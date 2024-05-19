package com.example.komalkotlin2024.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.komalkotlin2024.databinding.UsersListItemsBinding
import com.example.komalkotlin2024.mvvm.model.User
import java.util.ArrayList

class UsersListAdapter(val userList: ArrayList<User>): RecyclerView.Adapter<UsersListAdapter.UsersViewHolder>() {

    class UsersViewHolder(val binding: UsersListItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){
            binding.userName.text = user.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {

        return UsersViewHolder(UsersListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(userList.get(position))
    }
}