package com.cahyadesthian.ridretrofitagain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.ridretrofitagain.adapter.MyAdapter
import com.cahyadesthian.ridretrofitagain.databinding.ActivityMainBinding
import com.cahyadesthian.ridretrofitagain.model.Post
import com.cahyadesthian.ridretrofitagain.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainBinding: ActivityMainBinding
    private val theAdapter by lazy { MyAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(mainBinding.root)

        setupRecylerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        //viewModel.getCustomPostSomeQueries(2,"id","desc")

        //for post
//        val myPost = Post(2,2,"Caty","wiwiwi")
//        viewModel.pushPost(myPost)



        //post urlform
        //viewModel.pushPost2(2,5,"Hi","Come On you are awesome!")

        //tryin custom headir interceptor
        viewModel.getPost()


        viewModel.myResponse.observe(this, Observer {
           if(it.isSuccessful) {
               //theAdapter.setData(it.body() as ArrayList<Post>)

               Log.d("Main", it.body().toString())
               Log.d("Main", it.code().toString())
               Log.d("Main", it.message())
               Log.d("Main", it.headers().toString())


           } else {
               Toast.makeText(this,it.code(), Toast.LENGTH_SHORT).show()
           }
        })


    }

    private fun setupRecylerView() {
        mainBinding.apply {
            rvResponseMainUI.adapter = theAdapter
            rvResponseMainUI.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

}