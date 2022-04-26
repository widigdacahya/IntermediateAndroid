package com.cahyadesthian.chystoryapp.model

data class StoriesResponse(
    val error: Boolean,
    val message: String,
    val listStory: List<ItemListStory>
)
