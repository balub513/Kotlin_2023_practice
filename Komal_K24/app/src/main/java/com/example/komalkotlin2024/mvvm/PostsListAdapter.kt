package com.example.komalkotlin2024.mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.komalkotlin2024.R
import com.example.komalkotlin2024.mvvm.model.Post

class PostsListAdapter(private val postList: ArrayList<Post>) : RecyclerView.Adapter<PostsListAdapter.PostViewHolder>(){

    class PostViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.user_name)
        fun bind(post: Post){
            title.text = post.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.users_list_items, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
       return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind( postList[position])
    }
}