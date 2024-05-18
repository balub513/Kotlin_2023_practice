package com.example.test2023app.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test2023app.R
import com.example.test2023app.databinding.UserEntityItemBinding
import com.example.test2023app.di.module.db_module.UserEntity

class UserEntityAdapter constructor(var list: List<UserEntity>) :
    RecyclerView.Adapter<UserEntityAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: UserEntityItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(userEntity: UserEntity) {
            binding.tvName.text = userEntity.name
            binding.tvAge.text = userEntity.age
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<UserEntityItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.user_entity_item, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount() = list.size
}