package com.altaf.storyblog.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.altaf.storyblog.databinding.ItemStoryBinding
import com.altaf.storyblog.domain.model.Story

class StoryAdapter : ListAdapter<Story, StoryAdapter.StoryViewHolder>(StoryDiffCallback()) {

    var onItemClick: ((Story) -> Unit)? = null
    var onReadMoreClick: ((Story) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StoryViewHolder(
        private val binding: ItemStoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(getItem(adapterPosition))
                }
            }

            binding.btnReadMore.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onReadMoreClick?.invoke(getItem(adapterPosition))
                }
            }
        }

        fun bind(story: Story) {
            binding.apply {
                this.storyData = story
                executePendingBindings()
            }
        }
    }
}

class StoryDiffCallback : DiffUtil.ItemCallback<Story>() {
    override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem == newItem
    }
}
