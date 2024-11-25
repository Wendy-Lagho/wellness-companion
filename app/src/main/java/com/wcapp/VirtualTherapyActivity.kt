package com.wcapp
import ContactsAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VirtualTherapyActivity : AppCompatActivity() {

    private lateinit var btnAddContact: Button
    private lateinit var rvContacts: RecyclerView
    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_therapy)

        // Initialize UI components
        btnAddContact = findViewById(R.id.btnAddContact)
        rvContacts = findViewById(R.id.rvContacts)

        // Set click listener for "Add Contact" button
        btnAddContact.setOnClickListener {
            // Navigate to AddContactActivity
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }

        // Setup RecyclerView
        contactsAdapter = ContactsAdapter { contact ->
            // Handle contact click
            val intent = Intent(this, ChatActivity::class.java).apply {
                putExtra("CONTACT_ID", contact.id)
                putExtra("CONTACT_NAME", contact.name)
                putExtra("CONTACT_PHONE", contact.phone)
            }
            startActivity(intent)
        }
        rvContacts.apply {
            layoutManager = LinearLayoutManager(this@VirtualTherapyActivity)
            adapter = contactsAdapter
        }

        // Load contacts from database and display
        val dbHelper = DatabaseHelper(this)
        val contacts = dbHelper.getAllContacts()

        // Ensure contacts list is not null before submitting to adapter
        if (contacts != null) {
            contactsAdapter.submitList(contacts)
        }
    }
}
