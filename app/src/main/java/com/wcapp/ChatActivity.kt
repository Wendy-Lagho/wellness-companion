package com.wcapp

import Message
import MessageAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wcapp.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var messageAdapter: MessageAdapter
    private val messages: MutableList<Message> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        setupRecyclerView()
        setupInitialMessages()

        binding.buttonSend.setOnClickListener {
            sendMessage()
        }
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            val contactName = intent.getStringExtra("contact_name") ?: "Therapist" // Default name if not provided
            title = contactName
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupRecyclerView() {
        messageAdapter = MessageAdapter(messages)
        binding.recyclerViewMessages.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = messageAdapter
        }
    }

    private fun setupInitialMessages() {
        // Adding initial messages for demonstration
        messages.apply {
            add(Message(1, "Hello! How are you feeling today?", "Dr. Alex", System.currentTimeMillis()))
            add(Message(2, "I'm feeling a bit anxious.", "me", System.currentTimeMillis()))
            add(Message(3, "It's okay to feel anxious. Can you share more about what's making you anxious?", "Dr. Alex", System.currentTimeMillis()))
        }
        messageAdapter.notifyDataSetChanged()
    }

    private fun sendMessage() {
        val content = binding.editTextMessage.text.toString().trim()
        if (content.isNotEmpty()) {
            val message = Message(messages.size.toLong() + 1, content, "me", System.currentTimeMillis())
            messages.add(message)
            messageAdapter.notifyItemInserted(messages.size - 1)
            binding.recyclerViewMessages.scrollToPosition(messages.size - 1)
            binding.editTextMessage.text.clear()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
