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

    suspend fun getCustomPostSomeQueries(userId: Int,sort: String, order: String): Response<List<Post>> {
        return RetroiftInstance.api.getPostSomeQuery(userId,sort, order)
    }


    suspend fun getCustomPost2(userId: Int, options: Map<String, String>): Response<List<Post>> {
        return RetroiftInstance.api.getSomeQueryAnotherWay(userId, options)
    }


}