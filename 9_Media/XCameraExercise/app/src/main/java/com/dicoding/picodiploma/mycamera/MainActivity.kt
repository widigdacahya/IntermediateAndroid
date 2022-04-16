package com.dicoding.picodiploma.mycamera

import android.Manifest
import android.content.ContentResolver
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.dicoding.picodiploma.mycamera.data.FileUploadResponse
import com.dicoding.picodiploma.mycamera.databinding.ActivityMainBinding
import com.dicoding.picodiploma.mycamera.networking.ApiConfig

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class MainActivity : AppCompatActivity() {

    private var getFile: File? = null


    private lateinit var binding: ActivityMainBinding

    private lateinit var currentPhotoPath: String


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




    //for intent camera
    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if(it.resultCode == RESULT_OK) {

            //val imageBitmap = it.data?.extras?.get("data") as Bitmap
            val myFile = File(currentPhotoPath)
            val result = BitmapFactory.decodeFile(myFile.path)

            binding.previewImageView.setImageBitmap(result)
        }
    }


    //for intent gallery
    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->


        /**
         * we change below code for upload things
         * */
        /*
        if(result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, this@MainActivity)
            binding.previewImageView.setImageURI(selectedImg)
        }
        */

        if(result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri

            val contentResolver:ContentResolver = contentResolver
            val myFile = uriToFile(selectedImg, this@MainActivity)

            getFile = myFile

            binding.previewImageView.setImageURI(selectedImg)
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





    private fun uploadImage() {

        if(getFile!=null) {

            val file = getFile as File

            val description = "This desc of image".toRequestBody("text/plain".toMediaType())
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart : MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requestImageFile
            )

            val service = ApiConfig().getApiService().uploadImage(imageMultipart, description)
            service.enqueue(object : Callback<FileUploadResponse>{
                override fun onResponse(
                    call: Call<FileUploadResponse>,
                    response: Response<FileUploadResponse>
                ) {
                    if(response.isSuccessful) {
                        val responseBody = response.body()
                        if(responseBody != null && !responseBody.error) {
                            Toast.makeText(this@MainActivity, responseBody.message, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<FileUploadResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Retorift Instance Failed", Toast.LENGTH_SHORT).show()
                }

            })

        } else {
            Toast.makeText(this, "Image first", Toast.LENGTH_SHORT).show()
        }



    }

    private fun startGallery() {
        Toast.makeText(this, "Opening Gallery", Toast.LENGTH_SHORT).show()
        val intentGallery = Intent()
        intentGallery.action = ACTION_GET_CONTENT
        intentGallery.type = "image/*"
        val chooser = Intent.createChooser(intentGallery,"Pick Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun startTakePhoto() {
        Toast.makeText(this, " \uD83D\uDDBC Camera opened", Toast.LENGTH_SHORT).show()
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intentCamera.resolveActivity(packageManager)


        createTempFile(application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@MainActivity,
                "com.dicoding.picodiploma.mycamera",
                it
            )
            currentPhotoPath = it.absolutePath
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)

            launcherIntentCamera.launch(intentCamera)
        }
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