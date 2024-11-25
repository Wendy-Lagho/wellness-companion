package com.wcapp

data class GuidedMeditation(
    val id: Long,
    val title: String,
    val description: String,
    val videoUrl: String,
    val duration: Long,// Duration in milliseconds

)
