package com.wcapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CallActivity : AppCompatActivity() {

    private lateinit var btnStartCall: Button
    private var isCalling: Boolean = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        // Initialize views
        btnStartCall = findViewById(R.id.btnStartCall)

        // Example: Set initial call state
        updateCallState()

        // Button click listener to toggle call state
        btnStartCall.setOnClickListener {
            isCalling = !isCalling
            updateCallState()
        }
    }

    private fun updateCallState() {
        if (isCalling) {
            // Example: Call is active
            btnStartCall.text = "End Call"
            Toast.makeText(this, "Call started", Toast.LENGTH_SHORT).show()
            // Example: Implement call logic (e.g., start call service)
        } else {
            // Example: Call ended
            btnStartCall.text = "Start Call"
            Toast.makeText(this, "Call ended", Toast.LENGTH_SHORT).show()
            // Example: Implement call end logic (e.g., stop call service)
        }
    }
}
