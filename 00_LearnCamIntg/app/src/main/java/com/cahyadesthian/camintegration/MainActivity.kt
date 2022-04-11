package com.cahyadesthian.camintegration

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import com.cahyadesthian.camintegration.databinding.ActivityMainBinding
import java.io.File


//what number
//the number not siginifact i think
private const val REQUEST_CODE = 13579

/*
* File to write where photofile
* */
private lateinit var photoFile : File

private const val FILE_NAME = "photo.jpg"



class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)


        mainBinding.btnTakPhotoMainUI.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            photoFile = getPhotoFile(FILE_NAME)



            //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFile)
            //aboce code has issue,API >=24 not gonna work, so use below

            val fileProvider = FileProvider.getUriForFile(this,"com.cahyadesthian.fileprovider", photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

            if(takePictureIntent.resolveActivity(this.packageManager)!=null) {
                startActivityForResult(takePictureIntent,REQUEST_CODE)
            } else {
                Toast.makeText(this,"No Cam", Toast.LENGTH_SHORT).show()
            }


        }



    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //val takenImage = data?.extras?.get("data")as Bitmap
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            mainBinding.imgViewMainUI.setImageBitmap(takenImage)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }


    }


    private fun getPhotoFile(fileName: String): File {
        //Use getExternalFilseDir on context to access package-specific directories
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)

    }





}