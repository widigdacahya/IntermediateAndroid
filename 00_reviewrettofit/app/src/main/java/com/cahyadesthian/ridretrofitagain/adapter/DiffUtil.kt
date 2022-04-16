package com.cahyadesthian.ridretrofitagain.adapter

import androidx.recyclerview.widget.DiffUtil
import com.cahyadesthian.ridretrofitagain.model.Post

class DiffUtil(
    private val oldPostList: List<Post>,
    private val newPostList: List<Post>
) : DiffUtil.Callback(){

    override fun getOldListSize(): Int = oldPostList.size
    override fun getNewListSize(): Int = newPostList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPostList[oldItemPosition].id == newPostList[newItemPosition].id

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldPostList[oldItemPosition].id != newPostList[newItemPosition].id -> {
                false
            }
            oldPostList[oldItemPosition].userId != newPostList[newItemPosition].userId -> {
                false
            }
            oldPostList[oldItemPosition].body != newPostList[newItemPosition].body -> {
                false
            }
            oldPostList[oldItemPosition].title != newPostList[newItemPosition].title -> {
                false
            }
            else -> true
        }
    }

}