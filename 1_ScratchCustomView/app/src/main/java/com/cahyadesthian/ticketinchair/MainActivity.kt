package com.cahyadesthian.ticketinchair

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //menghilangkan toolbar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        setContentView(R.layout.activity_main)

        val seatView = findViewById<SeatsView>(R.id.seatsView_mainUI)
        val btnFinish = findViewById<Button>(R.id.btnFinish_mainUI)

        btnFinish.setOnClickListener{
            seatView.seat?.let {
                Toast.makeText(this, "Your choosen chair is ${it.name}.", Toast.LENGTH_SHORT).show()
            } ?: run {
                Toast.makeText(this, "Please choose on Chair.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}