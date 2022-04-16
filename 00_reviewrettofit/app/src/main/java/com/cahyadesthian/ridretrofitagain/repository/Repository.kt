package com.cahyadesthian.ridretrofitagain.repository

import com.cahyadesthian.ridretrofitagain.api.RetroiftInstance
import com.cahyadesthian.ridretrofitagain.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetroiftInstance.api.getPost()
    }

}