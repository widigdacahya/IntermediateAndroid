package com.cahyadesthian.chystoryapp.screen

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.chystoryapp.databinding.FragmentCameraStoryBinding
import com.cahyadesthian.chystoryapp.screen.util.timeStamp


class CameraStoryFragment : Fragment() {

    private var _camBinding : FragmentCameraStoryBinding? = null
    private val camBinding get() = _camBinding

    private var defaultCamera = CameraSelector.DEFAULT_BACK_CAMERA

    private var imageCamCaptured: ImageCapture? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _camBinding = FragmentCameraStoryBinding.inflate(inflater,container,false)
        val view = camBinding?.root

        return view

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
        openCam()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        camBinding?.ivBtnCamera?.setOnClickListener {
            capturePhoto()
        }

        camBinding?.ivSwitchcam?.setOnClickListener {
            defaultCamera = if (defaultCamera.equals(CameraSelector.DEFAULT_BACK_CAMERA)) CameraSelector.DEFAULT_FRONT_CAMERA else CameraSelector.DEFAULT_BACK_CAMERA
            openCam()
        }

    }


    private fun openCam() {
        val cameraProvider = ProcessCameraProvider.getInstance(context as Context)

        cameraProvider.addListener({
            val cameraProvider = cameraProvider.get()

            val preview = Preview.Builder().build().also { Preview->
                Preview.setSurfaceProvider(camBinding?.viewfinderCamera?.surfaceProvider)
            }

            imageCamCaptured = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this,defaultCamera,preview,imageCamCaptured)
            } catch (e: Exception) {
                Toast.makeText(activity, "Open Camera Failed", Toast.LENGTH_SHORT).show()
            }
        },ContextCompat.getMainExecutor(context as Context))
    }


    private fun capturePhoto() {
        val imageCaptured = imageCamCaptured?: return

        val name = "$timeStamp.jpg"

        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(context?.contentResolver as ContentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                ContentValues().apply {
                    put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
                    put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg")
                    put(MediaStore.Images.Media.TITLE,name)
            }
        ).build()

        imageCaptured.takePicture(outputOptions,ContextCompat.getMainExecutor(context as Context), object : ImageCapture.OnImageSavedCallback {

            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    setFragmentResult(
                        CAMERA_RES, bundleOf(
                            PICT to outputFileResults.savedUri,
                            IS_BACK_CAMERA to (defaultCamera == CameraSelector.DEFAULT_BACK_CAMERA)
                        )
                    )

                findNavController().navigateUp()
            }

            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(activity,"something worng when taking puctire", Toast.LENGTH_SHORT).show()
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _camBinding = null
    }


    companion object {
        const val CAMERA_RES = "camera_res"
        const val PICT = "picture"
        const val IS_BACK_CAMERA = "is_back_camera"
    }

}