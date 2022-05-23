package com.cahyadesthian.chystoryapp.screen.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cahyadesthian.chystoryapp.databinding.ItemStoryBinding
import com.cahyadesthian.chystoryapp.model.ItemListStory

class AdapterListStory(private val listStory: ArrayList<ItemListStory>) : RecyclerView.Adapter<AdapterListStory.StoryViewHolder>() {

    inner class StoryViewHolder(val binding: ItemStoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = listStory[position]


        holder.apply {
            binding.apply {
                ivStoryPreview.glideLoad(story.photoUrl)
                tvUserUploadBy.text = story.name
            }
            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(story,binding)
            }
        }

    }

    override fun getItemCount(): Int = listStory.size

    interface OnItemClickCallback {
        fun onItemClicked(story: ItemListStory, storyBinding: ItemStoryBinding)
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

}