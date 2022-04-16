package com.cahyadesthian.ridretrofitagain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cahyadesthian.ridretrofitagain.model.Post
import com.cahyadesthian.ridretrofitagain.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPosts: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val customPostWithSomeQueries : MutableLiveData<Response<List<Post>>> = MutableLiveData()

    val customPostAnotherWay : MutableLiveData<Response<List<Post>>> = MutableLiveData()


    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }


    fun getPost2(number:Int) {
        viewModelScope.launch {
            val response = repository.getPost2(number)
            myResponse2.value = response
        }
    }


    fun getCustomPosts(userId: Int) {
        viewModelScope.launch {
            val response = repository.getCustomPosts(userId)
            myCustomPosts.value = response
        }
    }


    fun getCustomPostSomeQueries(userId: Int, sort: String, order: String) {
        viewModelScope.launch {
            val response = repository.getCustomPostSomeQueries(userId, sort, order)
            customPostWithSomeQueries.value = response
        }
    }


    fun getCustomPostAnotherWay(userId: Int, options: Map<String,String>) {
        viewModelScope.launch {
            val response = repository.getCustomPost2(userId, options)
            customPostAnotherWay.value = response
        }
    }


    fun pushPost(post: Post) {
        viewModelScope.launch {
            val response = repository.pushPost(post)
            myResponse.value = response
        }
    }

    fun pushPost2(userId: Int,id:Int,title:String,body:String) {
        viewModelScope.launch {
            val response = repository.pushPost2(userId, id, title, body)
            myResponse.value = response
        }
    }

}