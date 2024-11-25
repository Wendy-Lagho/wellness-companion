package com.wcapp

// Import the activities that HomeActivity navigates t
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Find buttons by their IDs
        val btnMoodTracking: Button = findViewById(R.id.btnMoodTracking)
        val btnGuidedMeditation: Button = findViewById(R.id.btnGuidedMeditation)
        val btnVirtualTherapy: Button = findViewById(R.id.btnVirtualTherapy)
        val btnPeerSupport: Button = findViewById(R.id.btnPeerSupport)
        val btnEducationalResources: Button = findViewById(R.id.btnEducationalResources)

        // Set click listeners for each button to start corresponding activities
        btnMoodTracking.setOnClickListener {
            val intent = Intent(this, MoodTrackingActivity::class.java)
            startActivity(intent)
        }

        btnGuidedMeditation.setOnClickListener {
            val intent = Intent(this, GuidedMeditationActivity::class.java)
            startActivity(intent)
        }

        btnVirtualTherapy.setOnClickListener {
            val intent = Intent(this, VirtualTherapyActivity::class.java)
            startActivity(intent)
        }


        btnPeerSupport.setOnClickListener {
            val intent = Intent(this, PeerSupportActivity::class.java)
            startActivity(intent)
        }

        btnEducationalResources.setOnClickListener {
            val intent = Intent(this, EducationalResourcesActivity::class.java)
            startActivity(intent)
        }
    }
}
