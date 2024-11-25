package com.wcapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddContactActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var etContactName: EditText
    private lateinit var etContactPhone: EditText
    private lateinit var btnSaveContact: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        databaseHelper = DatabaseHelper(this)

        // Initialize UI components
        etContactName = findViewById(R.id.etContactName)
        etContactPhone = findViewById(R.id.etContactPhone)
        btnSaveContact = findViewById(R.id.btnSaveContact)

        // Set click listener for Save Contact button
        btnSaveContact.setOnClickListener {
            val contactName = etContactName.text.toString().trim()
            val contactPhone = etContactPhone.text.toString().trim()

            // Validate input
            if (contactName.isNotEmpty() && contactPhone.isNotEmpty()) {
                // Insert contact into database
                val result = databaseHelper.insertContact(contactName, contactPhone)
                if (result != -1L) {
                    // Contact saved successfully
                    Toast.makeText(this, "Contact saved successfully!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    // Handle insertion failure
                    Toast.makeText(this, "Failed to save contact. Please try again.", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Handle empty input fields
                Toast.makeText(this, "Please enter contact name and phone number.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
