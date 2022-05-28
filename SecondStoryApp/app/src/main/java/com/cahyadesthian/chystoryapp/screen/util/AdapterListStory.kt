package com.cahyadesthian.chystoryapp.screen.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cahyadesthian.chystoryapp.databinding.ItemStoryBinding
import com.cahyadesthian.chystoryapp.model.ItemListStory

class AdapterListStory : PagingDataAdapter<ItemListStory, AdapterListStory.StoryViewHolder>(DiffStory()) {

    inner class StoryViewHolder(val binding: ItemStoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {

        getItem(position)?.let { itemStory ->

            holder.binding.apply {
                ivStoryPreview.glideLoad(itemStory.photoUrl)
                tvUserUploadBy.text = itemStory.name
            }

            holder.itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(itemStory, holder.binding)
            }


        }


    }


    interface OnItemClickCallback {
        fun onItemClicked(story: ItemListStory, storyBinding: ItemStoryBinding)
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    class DiffStory : DiffUtil.ItemCallback<ItemListStory>(){
        override fun areItemsTheSame(oldItem: ItemListStory, newItem: ItemListStory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ItemListStory, newItem: ItemListStory): Boolean {
            return oldItem.name == newItem.name && oldItem.description == newItem.description && oldItem.photoUrl == newItem.photoUrl
        }


    }



}