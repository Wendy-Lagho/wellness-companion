package com.wcapp

data class ChatMessage(
    val senderId: String = "",
    val message: String = "",
    val timestamp: Long = 0
)
