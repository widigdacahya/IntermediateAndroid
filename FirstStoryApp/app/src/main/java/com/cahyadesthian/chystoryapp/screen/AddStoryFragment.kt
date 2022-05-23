package com.cahyadesthian.chystoryapp.screen

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentAddStoryBinding
import com.cahyadesthian.chystoryapp.screen.util.uriToFile
import com.cahyadesthian.chystoryapp.viewmodel.AddStoryViewModel
import java.io.File



class AddStoryFragment : Fragment() {

    private var _addStoryBinding : FragmentAddStoryBinding? = null
    private val addStoryBinding get() = _addStoryBinding

    private var userToken = ""

    private var fileImage: File? = null

    private val addStoryViewModel by viewModels<AddStoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _addStoryBinding = FragmentAddStoryBinding.inflate(inflater,container,false)
        val view = addStoryBinding?.root

        return view

    }

    private val launcherPermissionRequest = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if(!allPermissionsGranted()){
                Toast.makeText(activity,"ooops, permission things",Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
    }

    private val launcherIntentGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode == AppCompatActivity.RESULT_OK) {
            Toast.makeText(activity,"Picture taken",Toast.LENGTH_SHORT).show()

            val iamgeLoaded = it.data?.data as Uri

            fileImage = uriToFile(iamgeLoaded, context as Context)
            addStoryBinding?.ivPreviewUserStory?.setImageURI(iamgeLoaded)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingThings(false)
        userToken = AddStoryFragmentArgs.fromBundle(arguments as Bundle).token

        if(!allPermissionsGranted()) {
            launcherPermissionRequest.launch(REQUIRED_PERMISSIONS)
        }

        addStoryBinding?.btnGallery?.setOnClickListener {
            uploadFromGallery()
        }

        addStoryBinding?.btnCamera?.setOnClickListener {

        }



        addStoryBinding?.btnUpload?.setOnClickListener {
            uploadStoryToServer()
        }




        addStoryViewModel.apply {
            isLoading.observe(viewLifecycleOwner) {
                loadingThings(it)
            }

            isSuccess.observe(viewLifecycleOwner) {
                it.getIfNotHandled()?.let {
                    if(it) goListStoryPage()
                }
            }

            error.observe(viewLifecycleOwner) {
                it.getIfNotHandled()?.let { msg ->
                    Toast.makeText(activity,msg, Toast.LENGTH_SHORT).show()
                }
            }

        }


    }

    private fun loadingThings(isLoading: Boolean) {
        if(isLoading) {
            addStoryBinding?.loadingAddStory?.visibility = View.VISIBLE
        } else {
            addStoryBinding?.loadingAddStory?.visibility = View.GONE
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(activity?.baseContext as Context,it) == PackageManager.PERMISSION_GRANTED
    }

    private fun uploadFromGallery() {
        val intentToGallery = Intent().apply {
            action = Intent.ACTION_GET_CONTENT
            type = "image/*"
        }

        val chooser = Intent.createChooser(intentToGallery, "Take an image")
        launcherIntentGallery.launch(chooser)
    }

    private fun goListStoryPage() {
        setFragmentResult(
            ADD_RES, bundleOf(
                IS_SUCCESS to true
            )
        )

        findNavController().navigateUp()
    }

    private fun uploadStoryToServer() {

        val desc = addStoryBinding?.edtDescriptionStory?.text.toString()

        if(desc.isEmpty() || fileImage == null) {
            Toast.makeText(activity,"Can't leave empty",Toast.LENGTH_SHORT).show()
            return
        }

        addStoryViewModel.addStory(fileImage as File, desc, getString(R.string.bearerAuth,userToken) )

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _addStoryBinding = null
    }

    companion object {

        const val ADD_RES = "add_result"
        const val IS_SUCCESS = "is_success"

        private val REQUIRED_PERMISSIONS = if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else {
            arrayOf(Manifest.permission.CAMERA)
        }
    }

}