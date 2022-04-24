package com.cahyadesthian.chystoryapp.screen.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

open class ValidateEditText : AppCompatEditText {

    private var editTextValidity : EditTextValidation? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet): super(context,attrs) {
        init()
    }

    constructor(context: Context,attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    interface EditTextValidation {
        val errorMessage : String
        fun validate(editTextInput: String) :Boolean
    }

    fun setOnValidityCallback(editTextValidity : EditTextValidation) {
        this.editTextValidity = editTextValidity
    }


    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // nothim to do here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //nothin to do here
            }

            override fun afterTextChanged(s: Editable?) {
                inputValidation()
            }


        })
    }


    private fun inputValidation() : Boolean {

        val userInput = text.toString()
        val isValid = editTextValidity?.validate(userInput)?: true

        error = if(isValid) {
            null
        } else {
            editTextValidity?.errorMessage?: "Whoops"
        }


        return isValid
    }




}