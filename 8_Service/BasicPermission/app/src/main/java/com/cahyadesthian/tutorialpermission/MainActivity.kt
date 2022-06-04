package com.cahyadesthian.tutorialpermission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cahyadesthian.tutorialpermission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        mainBinding.reqPermissionBtn.setOnClickListener {
            //requestPermission()
            requesPermissionDoc()
            requesrPermissionLauncher
            Toast.makeText(this,"hmm",Toast.LENGTH_SHORT).show()
        }

    }

    private fun hasWriteExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationPermissionForeground() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    //need check if user Android Q to avoid crash
    private fun hasLocationPermissionBackground() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED



//    private fun requestPermission() {
//
//        //permission need to be defined on string array
//        var permissionToRequest = mutableListOf<String>()
//
//
//        /*
//        * here ide is make some permission, if one of them granted, request others
//        * */
//
//        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
//
//            if(!hasWriteExternalStoragePermission()) {
//                permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            }
//
//            if(!hasLocationPermissionForeground()) {
//                permissionToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
//            }
//
//            if(!hasLocationPermissionBackground()) permissionToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
//
//
//
//        }
//
//
//        if(permissionToRequest.isNotEmpty()) {
//            ActivityCompat.requestPermissions(this,permissionToRequest.toTypedArray(),0)
//        }
//
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if(requestCode==0 && grantResults.isNotEmpty()) {
//
//            for(i in grantResults.indices) {
//
//                //if a permission granted, will print out to logcat
//                if(grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                    Log.d("Permission", "${permissions[i]} granted ")
//                }
//
//            }
//
//        }
//
//
//    }


    private val requesrPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {

        isGranted: Boolean ->

        if(isGranted) {
            Log.d("permisi", "Granted ")
        } else {
            Log.d("permisi", "Denied ")
        }

    }

    private fun requesPermissionDoc() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) -> {
                Toast.makeText(this,"Need it",Toast.LENGTH_SHORT).show()
                requesrPermissionLauncher.launch(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            } else -> {
                requesrPermissionLauncher.launch(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }
    }

}