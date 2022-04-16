package com.cahyadesthian.ridretrofitagain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cahyadesthian.ridretrofitagain.databinding.ActivityMainBinding
import com.cahyadesthian.ridretrofitagain.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(mainBinding.root)


        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        //viewModel.getPost()




        mainBinding.btnGetMainUI.setOnClickListener {
            val theNumber = mainBinding.edtNumberMainUI.text.toString()

            viewModel.getPost2(theNumber.toInt())
            //viewModel.getPost2(Integer.parseInt(theNumber))

            viewModel.myResponse2.observe(this, Observer { response ->

                if(response.isSuccessful) {
                    //Log.d("Response ", response.body()?.userId.toString())
                    //Log.d("Response ", response.body()?.id.toString())
                    //Log.d("Response ", response.title.toString())
                    mainBinding.textViewMainUI.text = response.body()?.toString()
                    //Log.d("Response ", response.body()?.body.toString())
                } else {
                    Log.d("ResponseFail", response.errorBody().toString())
                    mainBinding.textViewMainUI.text = response.code().toString()
                }

            })


        }

    }
}