package com.cahyadesthian.imagerecognition

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.cahyadesthian.imagerecognition.databinding.ActivityMainBinding
import com.cahyadesthian.imagerecognition.ml.MobilenetV110224Quant
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    lateinit var bitmap: Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        val fileName = "label.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use { it.readText() }
        var thingList = inputString.split("\n")

        mainBinding.btnSelect.setOnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult(intent,100)
        }

        mainBinding.btnPredict.setOnClickListener {
            var resizedImg: Bitmap = Bitmap.createScaledBitmap(bitmap,224,224,true)

            val model = MobilenetV110224Quant.newInstance(this)

            var tbuffer = TensorImage.fromBitmap(resizedImg)
            var byteBuffer = tbuffer.buffer


            // Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)
            inputFeature0.loadBuffer(byteBuffer)

            // Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            var max = getMax(outputFeature0.floatArray)

            mainBinding.tvDataPrediction.text = thingList[max]

            // Releases model resources if no longer used.
            model.close()



        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mainBinding.ivImg.setImageURI(data?.data)

        var uri = data?.data
        bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,uri)

    }

    private fun getMax(arr: FloatArray) : Int {
        var index = 0
        var min = 0.0f

        for(i in 0..1000) {
            if(arr[i]>min) {
                index = i
                min = arr[i]
            }
        }

        return index
    }

}