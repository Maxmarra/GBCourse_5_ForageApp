package com.example.forageapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.forageapp.databinding.ListItemForageableBinding
import com.example.forageapp.model.Item

/**
 * ListAdapter for the list of [Item]s retrieved from the database
 */
class ForageableListAdapter(
    private val clickListener: (Item) -> Unit
) : ListAdapter<Item, ForageableListAdapter.ForageableViewHolder>(DiffCallback) {

    class ForageableViewHolder(
        private var binding: ListItemForageableBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(forageable: Item) {
            binding.forageable = forageable
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForageableViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ForageableViewHolder(
            ListItemForageableBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ForageableViewHolder, position: Int) {
        val forageable = getItem(position)
        holder.itemView.setOnClickListener{
            clickListener(forageable)
        }
        holder.bind(forageable)
    }
}
