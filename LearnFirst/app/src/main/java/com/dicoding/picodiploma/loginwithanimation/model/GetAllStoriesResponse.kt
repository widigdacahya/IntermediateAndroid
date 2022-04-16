package com.dicoding.picodiploma.loginwithanimation.model

data class GetAllStoriesResponse(
	val listStory: List<ListStoryItem?>? = null,
	val error: Boolean? = null,
	val message: String? = null
)

data class ListStoryItem(
	val photoUrl: String? = null,
	val createdAt: String? = null,
	val name: String? = null,
	val description: String? = null,
	val lon: Double? = null,
	val id: String? = null,
	val lat: Double? = null
)

