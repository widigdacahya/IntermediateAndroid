package com.cahyadesthian.ridretrofitagain.repository

import com.cahyadesthian.ridretrofitagain.api.RetroiftInstance
import com.cahyadesthian.ridretrofitagain.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetroiftInstance.api.getPost()
    }

    suspend fun getPost2(number: Int): Response<Post> {
        return RetroiftInstance.api.getPost2(number)
    }

    suspend fun getCustomPosts(userId: Int): Response<List<Post>> {
        return RetroiftInstance.api.getCustomPost(userId)
    }

}