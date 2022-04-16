package com.dicoding.picodiploma.mycamera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dicoding.picodiploma.mycamera.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    //for intent camera
    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if(it.resultCode == RESULT_OK) {
            val imageBitmap = it.data?.extras?.get("data") as Bitmap
            binding.previewImageView.setImageBitmap(imageBitmap)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(!allPermissionsGranted()) {

            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )

        }

        binding.cameraXButton.setOnClickListener { startCameraX() }
        binding.cameraButton.setOnClickListener { startTakePhoto() }
        binding.galleryButton.setOnClickListener { startGallery() }
        binding.uploadButton.setOnClickListener { uploadImage() }
    }

    private fun startCameraX() {
        Toast.makeText(this, "\uD83D\uDCF8 Camera Openend", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent) //in the previous it was confusing wth is these thing, but ternyata ini val that it selfmade that made after few steps we confused(in that time we use startActivity)
        //startActivity(intent)
    }


    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {

        if(it.resultCode == CAMERA_X_RESULT) {

            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            val result = BitmapFactory.decodeFile(myFile.path)
            //the picture rotated, so need rotatie it(it was provided on statrter project utils)

            //val result = rotateBitmap(BitmapFactory.decodeFile(myFile.path),isBackCamera)



            binding.previewImageView.setImageBitmap(result)

        }

    }


    private fun uploadImage() {
        Toast.makeText(this, "Fitur ini belum tersedia", Toast.LENGTH_SHORT).show()
    }

    private fun startGallery() {
        Toast.makeText(this, "Fitur ini belum tersedia", Toast.LENGTH_SHORT).show()
    }

    private fun startTakePhoto() {
        Toast.makeText(this, " \uD83D\uDDBC Camera opened", Toast.LENGTH_SHORT).show()
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        launcherIntentCamera.launch(intentCamera)
    }

//    private fun startCameraX() {
//        //Toast.makeText(this, "Fitur ini belum tersedia", Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, "\uD83D\uDCF8 Camera Openend", Toast.LENGTH_SHORT).show()
//        val intentOpenCamX = Intent(this, CameraActivity::class.java)
//        launcherIntentCameraX.launch(intentOpenCamX)
//    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_PERMISSIONS) {
            if(!allPermissionsGranted()) {
                Toast.makeText(this,"Permission Denied", Toast.LENGTH_SHORT).show()
                finish()
            }
        }


    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext,it) == PackageManager.PERMISSION_GRANTED
    }




    companion object {
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

}