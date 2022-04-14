package com.dicoding.picodiploma.mycamera

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.dicoding.picodiploma.mycamera.databinding.ActivityCameraBinding


/*
*
* digunakan untuk menampilkan kamera dan mengambil gambar darinya.
**/

class CameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraBinding

    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.captureImage.setOnClickListener { takePhoto() }
        binding.switchCamera.setOnClickListener {

            cameraSelector = if(cameraSelector.equals(CameraSelector.DEFAULT_BACK_CAMERA)) CameraSelector.DEFAULT_FRONT_CAMERA
                                else CameraSelector.DEFAULT_BACK_CAMERA

            startCamera() }
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUI()
        startCamera()
    }

    private fun takePhoto() {
       // takePhoto

        var imageCaptured = imageCapture ?: return

        val photoFile = createFile(application)

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
            imageCaptured.takePicture(
                outputOptions,ContextCompat.getMainExecutor(this),
                object : ImageCapture.OnImageSavedCallback{

                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        Toast.makeText(this@CameraActivity, "Image Captured \uD83D\uDE46\uD83C\uDFFB\u200D♀️", Toast.LENGTH_SHORT).show()

                        val intentCapturedImage = Intent()
                        intentCapturedImage.putExtra("picture",photoFile)
                        intentCapturedImage.putExtra(
                            "isBackCamera",
                            cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA
                        )
                        setResult(MainActivity.CAMERA_X_RESULT,intentCapturedImage)
                        finish()

                    }

                    override fun onError(exception: ImageCaptureException) {
                       Toast.makeText(this@CameraActivity, "Capture Failed \uD83D\uDE47\uD83C\uDFFB\u200D♀️", Toast.LENGTH_SHORT).show()
                    }

                }
            )


    }



//    private var imageCapture: ImageCapture? = null
//    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    private fun startCamera() {
        // showCamera

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this,cameraSelector,preview,imageCapture)
            } catch (exc: Exception) {
                Toast.makeText(this@CameraActivity, "Fail to show Camera \uD83D\uDCF7", Toast.LENGTH_SHORT).show()
            }

        },ContextCompat.getMainExecutor(this))

    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}