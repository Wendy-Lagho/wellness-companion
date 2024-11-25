// Therapist.kt
package com.wcapp

data class Therapist(
    val id: String = "",
    val name: String = "",
    val iconUrl: String = ""
) {
    val iconResId: Int
        get() = when (name) {
            "Dr. Smith" -> R.drawable.ic_therapist// Replace with your actual drawable resource
            "Dr. Johnson" -> R.drawable.ic_therapist // Replace with your actual drawable resource
            else -> R.drawable.ic_therapist// Replace with your default drawable resource
        }


    data class ChatMessage(
        val id: String = "",
        val senderId: String = "",
        val message: String = "",
        val timestamp: Long = 0,
        val sender: String
    )
}
