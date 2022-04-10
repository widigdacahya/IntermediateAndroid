package com.cahyadesthian.camintegration

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.cahyadesthian.camintegration.databinding.ActivityMainBinding


//what number
private const val REQUEST_CODE = 13579

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)


        mainBinding.btnTakPhotoMainUI.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if(takePictureIntent.resolveActivity(this.packageManager)!=null) {
                startActivityForResult(takePictureIntent,REQUEST_CODE)
            } else {
                Toast.makeText(this,"No Cam", Toast.LENGTH_SHORT).show()
            }


        }



    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = data?.extras?.get("data")as Bitmap
            mainBinding.imgViewMainUI.setImageBitmap(takenImage)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }


    }





}