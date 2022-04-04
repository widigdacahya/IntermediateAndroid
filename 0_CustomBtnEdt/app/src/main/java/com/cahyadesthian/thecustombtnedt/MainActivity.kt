package com.cahyadesthian.thecustombtnedt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.cahyadesthian.thecustombtnedt.customview.MyButton
import com.cahyadesthian.thecustombtnedt.customview.MyEditText
import com.cahyadesthian.thecustombtnedt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    private lateinit var myCustomButton : MyButton
    private lateinit var myCustomEditText : MyEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        myCustomButton = mainBinding.myBtnMainUI
        myCustomEditText = mainBinding.myEdtMainUI

        setMyCustomButtonEnability()

        myCustomEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setMyCustomButtonEnability()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        myCustomButton.setOnClickListener {
            Toast.makeText(this@MainActivity,myCustomEditText.text, Toast.LENGTH_SHORT).show()
        }

    }


    // for checking is there any text in edt, of there any
    // button will enabled
    private fun setMyCustomButtonEnability() {
        val textInEdt = myCustomEditText.text
        myCustomButton.isEnabled = textInEdt != null && textInEdt.toString().isNotEmpty()
    }

}