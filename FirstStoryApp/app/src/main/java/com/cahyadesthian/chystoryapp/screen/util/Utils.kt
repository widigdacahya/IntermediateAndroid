package com.cahyadesthian.chystoryapp.screen.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cahyadesthian.chystoryapp.R

fun ImageView.glideLoad(dataUrl: String) {
    Glide.with(this.context)
        .load(dataUrl)
        .centerCrop()
        .placeholder(R.drawable.placeholderpict)
        .into(this)
}