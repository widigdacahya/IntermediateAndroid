package com.cahyadesthian.textclassificationcodelabs.helpers

import android.content.Context
import android.util.Log
import org.tensorflow.lite.support.label.Category
import org.tensorflow.lite.task.text.nlclassifier.NLClassifier
import java.io.IOException


class TextClassificationClient {

    private val MODEL_PATH = "model.tflite"
    private val TAG = "CommentSpam"
    private var context: Context? = null

    var classifier: NLClassifier? = null

    //lateinit var classifier: NLClassifier

    fun TextClassificationClient(context: Context) {
        this.context = context
    }

    fun load() {
        try {
            classifier = NLClassifier.createFromFile(context, MODEL_PATH)
        } catch (e: IOException) {
            e.message?.let { Log.e(TAG, it) }
        }
    }

    fun unload() {
        classifier?.close()
        //classifier = null
    }

    fun classify(text: String): List<Category> {
        val apiResult : List<Category> = (classifier?.classify(text) ?: "") as List<Category>
        return apiResult
    }

}