package com.cahyadesthian.chystoryapp.screen.customview

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText


/*
* Name Edit Text
* */
class TheCustomEditText : AppCompatEditText, View.OnTouchListener {


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
        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //nothing to do here
            }

            override fun afterTextChanged(s: Editable?) {
                //do nothing
            }

        })

    }


    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        return false
    }


    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)
        hint = "Your beautiful name"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START

    }



}