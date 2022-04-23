package com.cahyadesthian.chystoryapp.screen.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.cahyadesthian.chystoryapp.R

/*
* Name Edit Text
* */
class TheCustomEditText : AppCompatEditText, View.OnTouchListener {

    //like using material input edit text clear text
    private lateinit var clearAllTextBtnIc: Drawable
    private lateinit var profileIcStart: Drawable



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
        clearAllTextBtnIc = ContextCompat.getDrawable(context, R.drawable.ic_round_close_orange_24) as Drawable
        profileIcStart = ContextCompat.getDrawable(context,R.drawable.ic_round_person_orange_24) as Drawable
        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.toString().isNotEmpty()) {
                    showClearIcBtn()
                    maintainIcProfile()
                }
                else hideClearIcBtn()
            }

            override fun afterTextChanged(s: Editable?) {
                //do nothing
            }

        })

    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {

        //compundDrawable = endOfTheText
        if(compoundDrawables[2] != null) {

            val clearButtonStart: Float
            val clearButtonEnd: Float
            var isClearButtonClicked = false

            if(layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                clearButtonEnd = (clearAllTextBtnIc.intrinsicWidth + paddingStart).toFloat()

                when {
                    event.x < clearButtonEnd -> isClearButtonClicked = true
                }

            } else {
                clearButtonStart = (width - paddingEnd - clearAllTextBtnIc.intrinsicWidth).toFloat()

                when {
                    event.x > clearButtonStart -> isClearButtonClicked = true
                }

            }

            if(isClearButtonClicked) {

                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        clearAllTextBtnIc = ContextCompat.getDrawable(context, R.drawable.ic_round_close_orange_24) as Drawable
                        showClearIcBtn()
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        clearAllTextBtnIc = ContextCompat.getDrawable(context, R.drawable.ic_round_close_orange_24) as Drawable

                        when {
                            text != null -> text?.clear()
                        }
                        hideClearIcBtn()
                        return true
                    }
                    else -> return false
                }

            }
            return false

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


    private fun showClearIcBtn() {
        setButtonDeawables(endOfTheText =  clearAllTextBtnIc)
    }

    private fun hideClearIcBtn() {
        setButtonDeawables()
    }

    private fun maintainIcProfile() {
        setButtonDeawables(startOfTheText = profileIcStart)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = "Your beautiful name"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

}