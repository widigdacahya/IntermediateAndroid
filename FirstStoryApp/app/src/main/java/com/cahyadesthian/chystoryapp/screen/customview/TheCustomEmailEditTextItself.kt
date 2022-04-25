package com.cahyadesthian.chystoryapp.screen.customview

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

class TheCustomEmailEditTextItself : AppCompatEditText, View.OnTouchListener {

    private var emailEditTextValidity : EmailEditTextValidationFix? = null

    interface EmailEditTextValidationFix {
        val errorMessageEmail : String
        fun emailValidate(editTextInput: String) :Boolean

    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet): super(context,attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
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
                emailInputValidation()
            }


        })
    }


    private fun emailInputValidation() : Boolean {

        val userInput = text.toString()
        val isValid = emailEditTextValidity?.emailValidate(userInput)?: Patterns.EMAIL_ADDRESS.matcher(userInput).matches()

        error = if(isValid) {
            null
        } else {
            emailEditTextValidity?.errorMessageEmail?: "Hey, Please input correct email"
        }


        return isValid
    }



    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return false
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = "Most Awesome People's Email"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

}