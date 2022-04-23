package com.cahyadesthian.chystoryapp.screen.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.cahyadesthian.chystoryapp.R
import kotlin.properties.Delegates

/*
* Password Edit Text
* */
class ThePasswordlEditText : View.OnTouchListener,ValidateEditText {

    private lateinit var icEye : Drawable
    private lateinit var iceEyeOff : Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context,attrs) {
        init()
    }

    constructor(context: Context, attrs:AttributeSet,defStyleAttr: Int) : super(context,attrs,defStyleAttr) {
        init()
    }


    private fun init() {

        icEye = ContextCompat.getDrawable(context, R.drawable.ic_baseline_remove_red_eye_24) as Drawable

        setButtonDeawables(endOfTheText = icEye)

        setOnTouchListener(this)

    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {

        val eyeIcStart : Float
        val eyeIcEnd : Float
        var icClicked = false

        if(layoutDirection == View.LAYOUT_DIRECTION_RTL) {
            eyeIcEnd = (icEye.intrinsicWidth + paddingStart).toFloat()

            if(event.x < eyeIcEnd) {
                icClicked = false
            }

        } else {

            eyeIcStart = (width - paddingEnd - icEye.intrinsicWidth).toFloat()
            if(event.x > eyeIcStart) {
                icClicked = true
            }

        }


        if(icClicked) {

            var selection = selectionEnd

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
        hint = "Amazing Password"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

}