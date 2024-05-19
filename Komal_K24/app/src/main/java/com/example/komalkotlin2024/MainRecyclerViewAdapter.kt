package com.example.komalkotlin2024

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView.*


class MainRecyclerViewAdapter(private val list: ArrayList<String>, private val onItemClick: (Int) ->Unit) : Adapter<MainRecyclerViewAdapter.MainViewHolder>(){

    class MainViewHolder( view: View, val onItemClick: (Int) -> Unit): ViewHolder(view) {
        private val text_tv = view.findViewById<TextView>(R.id.text_title)
        fun bind(text: String){
            text_tv.text = text
        }


    }

    fun updateData(items: ArrayList<String>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
       val  view = LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false)
        return MainViewHolder(view, onItemClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener({ onItemClick(position )})
    }

}