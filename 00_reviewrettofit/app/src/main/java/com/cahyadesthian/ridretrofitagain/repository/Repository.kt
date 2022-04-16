package com.cahyadesthian.ridretrofitagain.repository

import com.cahyadesthian.ridretrofitagain.api.RetroiftInstance
import com.cahyadesthian.ridretrofitagain.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(auth:String): Response<Post> {
        return RetroiftInstance.api.getPost(auth)
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


    suspend fun pushPost(post: Post): Response<Post> {
        return RetroiftInstance.api.pushPost( post)
    }

    suspend fun pushPost2(userId: Int,id:Int,title:String,body:String): Response<Post> {
        return RetroiftInstance.api.pushPost2(userId, id, title, body)
    }


}