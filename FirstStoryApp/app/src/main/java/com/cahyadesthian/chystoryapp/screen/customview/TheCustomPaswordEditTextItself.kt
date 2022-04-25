package com.cahyadesthian.chystoryapp.screen.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.cahyadesthian.chystoryapp.R

class TheCustomPaswordEditTextItself : AppCompatEditText, View.OnTouchListener {

    private lateinit var icEye : Drawable
    private var editTextValidity : EditTextValidationFix? = null

    interface EditTextValidationFix {
        val errorMessage : String
        fun validate(editTextInput: String) :Boolean

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

        icEye = ContextCompat.getDrawable(context, R.drawable.ic_baseline_remove_red_eye_24) as Drawable

        setButtonDeawables(endOfTheText = icEye)

        setOnTouchListener(this)


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
        val isValid = editTextValidity?.validate(userInput)?: (userInput.length>=6)

        error = if(isValid) {
            null
        } else {
            editTextValidity?.errorMessage?: "Whoops Hey,at least need 6 character here."
        }




        return isValid
    }



    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val eyeIcStart : Float
        val eyeIcEnd : Float
        var icClicked = false

        if(layoutDirection == View.LAYOUT_DIRECTION_RTL) {
            eyeIcEnd = (icEye.intrinsicWidth + paddingStart).toFloat()

            if (event != null) {
                if(event.x < eyeIcEnd) {
                    icClicked = false
                }
            }

        } else {

            eyeIcStart = (width - paddingEnd - icEye.intrinsicWidth).toFloat()
            if (event != null) {
                if(event.x > eyeIcStart) {
                    icClicked = true
                }
            }

        }


        if(icClicked) {

            var selection = selectionEnd

            if (event != null) {
                when(event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        transformationMethod = HideReturnsTransformationMethod.getInstance()
                        setSelection(selection)
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        transformationMethod = PasswordTransformationMethod.getInstance()
                        setSelection(selection)
                        return true
                    }
                }
            }

        }


        return false
    }

    private fun setButtonDeawables(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText, topOfTheText,endOfTheText,bottomOfTheText
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = "Your Amazing Password"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }
}