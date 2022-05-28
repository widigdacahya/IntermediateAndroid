package com.cahyadesthian.chystoryapp.screen.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cahyadesthian.chystoryapp.databinding.ItemLoadingBinding

class AdapterLoadingState(private val retry: () -> Unit): LoadStateAdapter<AdapterLoadingState.ViewHolder>() {


    inner class ViewHolder(private val loadingBinding: ItemLoadingBinding, retry: () -> Unit): RecyclerView.ViewHolder(loadingBinding.root) {
        init {
            loadingBinding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }


        fun bind(loadState: LoadState) {

            loadingBinding.apply {

                if(loadState is LoadState.Error) tvErrormsgLoadingitem.text = loadState.error.localizedMessage

                pbLoadingitem.isVisible = loadState is LoadState.Loading
                tvErrormsgLoadingitem.isVisible = loadState is LoadState.Error
                btnRetry.isVisible = loadState is LoadState.Error

            }

        }


    }


    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {

        val loadItemBinding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(loadItemBinding, retry)
    }


}