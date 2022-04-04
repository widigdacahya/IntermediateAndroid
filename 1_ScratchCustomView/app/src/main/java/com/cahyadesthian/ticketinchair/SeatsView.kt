package com.cahyadesthian.ticketinchair

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat

class SeatsView : View {

    private val seats: ArrayList<Seat> = arrayListOf()


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context,attrs: AttributeSet, defStyleAttr:Int) : super(context, attrs, defStyleAttr)



    init {

        seats.apply {
            add(Seat(id=1, name= "a1", isBooked = false))
            add(Seat(id=2, name= "a2", isBooked = false))

            add(Seat(id=3, name= "b1", isBooked = false))
            add(Seat(id=4, name= "b2", isBooked = false))

            add(Seat(id=5, name= "c1", isBooked = false))
            add(Seat(id=6, name= "c2", isBooked = false))

            add(Seat(id=7, name= "d1", isBooked = false))
            add(Seat(id=8, name= "d2", isBooked = false))

        }

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = getDefaultSize(suggestedMinimumWidth,widthMeasureSpec)
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)

        val halfIfWidth = width/2
        val halfOfHeight = height/2
        var value = -600f



        /*
        * Di sini Anda telah melakukan perulangan dari 0 sampai 7 untuk memeriksa
        * apakah kursi berada di sebelah kanan atau kiri. Selain itu, Anda juga
        * telah menjelaskan pada baris berapa kursi tersebut akan digambar.
        *
        * */

        for(i in 0..7) {

            if(i.mod(2) == 0) {
                seats[i].apply {
                    x = halfIfWidth - 300f
                    y = halfOfHeight + value
                }
            } else {
                seats[i].apply {
                    x = halfIfWidth + 100f
                    y = halfOfHeight + value
                }
                value += 300f
            }

        }


    }



    /**
     * Drawing Seats
     *
     * */

    var seat: Seat? = null

    private val backgroundPaint = Paint()
    private val armrestPaint = Paint()      //sandaran tangan
    private val bottomSeatPaint = Paint()   //bagian bawah kursi
    private val mBounds = Rect()
    private val numberSeatPaint = Paint(Paint.FAKE_BOLD_TEXT_FLAG)
    private val titlePaint = Paint(Paint.FAKE_BOLD_TEXT_FLAG)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for(seat in seats) {
            drawSeat(canvas, seat)
        }


    }


    /*penggambaran seat dengan given data seat */
    private fun drawSeat(canvas: Canvas? , seat:Seat) {

        //mengatur warna ketika sudah dibooking
        if(seat.isBooked) {
            backgroundPaint.color = ResourcesCompat.getColor(resources, R.color.gray_300,null)
            armrestPaint.color = ResourcesCompat.getColor(resources, R.color.gray_300,null)
            bottomSeatPaint.color = ResourcesCompat.getColor(resources, R.color.gray_300,null)
            numberSeatPaint.color = ResourcesCompat.getColor(resources, R.color.black,null)
        } else {
            backgroundPaint.color = ResourcesCompat.getColor(resources, R.color.red_600,null)
            armrestPaint.color = ResourcesCompat.getColor(resources, R.color.red_800,null)
            bottomSeatPaint.color = ResourcesCompat.getColor(resources, R.color.red_400,null)
            numberSeatPaint.color = ResourcesCompat.getColor(resources, R.color.gray_300,null)
        }


        //penyimpanan state
        canvas?.save()



        //background
        canvas?.translate(seat.x as Float, seat.y as Float)

        val backgroundPath = Path()
        backgroundPath.addRect(0f,0f, 200f, 200f, Path.Direction.CCW)
        backgroundPath.addCircle(100f,50f, 75f, Path.Direction.CCW)
        canvas?.drawPath(backgroundPath, backgroundPaint)



        //sandaran tangan
        val armrestPath = Path()
        armrestPath.addRect(0f,0f,50f,200f,Path.Direction.CCW)
        canvas?.drawPath(armrestPath,armrestPaint)
        canvas?.translate(150f,0f)
        armrestPath.addRect(0f,0f,50f,200f, Path.Direction.CCW)
        canvas?.drawPath(armrestPath,armrestPaint)



        //bagian bawah kursi
        canvas?.translate(-150f, 175f)
        val bottomSeatPath = Path()
        bottomSeatPath.addRect(0f, 0f, 200f, 25f, Path.Direction.CCW)
        canvas?.drawPath(bottomSeatPath, bottomSeatPaint)



        //penulisan nomer kursi
        canvas?.translate(0f, -175f)
        numberSeatPaint.apply {
            textSize = 50f
            numberSeatPaint.getTextBounds(seat.name, 0, seat.name.length,mBounds)

        }
        canvas?.drawText(seat.name,100f-mBounds.centerX(),100f,numberSeatPaint)


        //pengembalian ke pengaturan sebelumnya
        canvas?.restore()

    }



}