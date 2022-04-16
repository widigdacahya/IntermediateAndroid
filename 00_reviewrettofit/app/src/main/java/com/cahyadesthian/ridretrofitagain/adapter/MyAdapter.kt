package com.cahyadesthian.ridretrofitagain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cahyadesthian.ridretrofitagain.databinding.RowLayoutBinding
import com.cahyadesthian.ridretrofitagain.model.Post

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var oldPostList = ArrayList<Post>()

    inner class MyViewHolder(val rowBinding: RowLayoutBinding) : RecyclerView.ViewHolder(rowBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.rowBinding.tvUserIdItem.text = oldPostList[position].userId.toString()
        holder.rowBinding.tvIdPostItem.text = oldPostList[position].id.toString()
        holder.rowBinding.tvTitleItem.text = oldPostList[position].title
        holder.rowBinding.tvDescriptionItem.text = oldPostList[position].body
    }

    override fun getItemCount(): Int = oldPostList.size

    fun setData(newPostList: ArrayList<Post>) {
        val diffUtil = DiffUtil(oldPostList,newPostList)
        val diffResult = androidx.recyclerview.widget.DiffUtil.calculateDiff(diffUtil)
        oldPostList = newPostList
        diffResult.dispatchUpdatesTo(this)
    }

}