package com.example.test2023app.view.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.example.test2023app.model.response.players_match.Player
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import com.example.test2023app.R
import android.widget.Toast
import com.example.test2023app.BR
import com.example.test2023app.databinding.ItemRowPlayerBinding

class PlayersAdapter(private val playersList: List<Player>, private val context: Context) :
    RecyclerView.Adapter<PlayersAdapter.MyViewHolder>(), CustomClickListener {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = DataBindingUtil.inflate<ItemRowPlayerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_row_player, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataModel = playersList[position]
        holder.bind(dataModel)
        holder.itemRowBinding.itemClickListener = this
    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    override fun cardClicked(f: Player) {
        Toast.makeText(
            context, "You clicked " + f.name,
            Toast.LENGTH_LONG
        ).show()
    }

    class MyViewHolder(var itemRowBinding: ItemRowPlayerBinding) : RecyclerView.ViewHolder(
        itemRowBinding.root
    ) {
        fun bind(obj: Any?) {
            itemRowBinding.setVariable(BR.model, obj)
            itemRowBinding.executePendingBindings()
        }
    }
}