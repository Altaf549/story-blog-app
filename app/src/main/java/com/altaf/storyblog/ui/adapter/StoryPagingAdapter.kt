package com.altaf.storyblog.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.altaf.storyblog.databinding.ItemStoryBinding
import com.altaf.storyblog.domain.model.Story

class StoryPagingAdapter : PagingDataAdapter<Story, StoryPagingAdapter.StoryPagingViewHolder>(StoryPagingDiffCallback()) {

    var onItemClick: ((Story) -> Unit)? = null
    var onReadMoreClick: ((Story) -> Unit)? = null
    var onLoadMore: (() -> Unit)? = null

    override fun onBindViewHolder(holder: StoryPagingViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { story ->
            holder.bind(story)
            
            // Load more items when reaching the end of the list
            if (position == itemCount - 1) {
                onLoadMore?.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryPagingViewHolder {
        val binding = ItemStoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StoryPagingViewHolder(binding)
    }

    inner class StoryPagingViewHolder(
        private val binding: ItemStoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    getItem(bindingAdapterPosition)?.let { story ->
                        onItemClick?.invoke(story)
                    }
                }
            }

            binding.btnReadMore.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    getItem(bindingAdapterPosition)?.let { story ->
                        onReadMoreClick?.invoke(story)
                    }
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

class StoryPagingDiffCallback : DiffUtil.ItemCallback<Story>() {
    override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem == newItem
    }
}
