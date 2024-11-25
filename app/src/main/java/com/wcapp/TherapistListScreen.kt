package com.wcapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.wcapp.Therapist
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Composable
fun TherapistListScreen(onTherapistClick: (Therapist) -> Unit) {
    val repository = TherapistRepository()
    val therapists = remember { mutableStateOf(listOf<Therapist>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            therapists.value = repository.getTherapists()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        therapists.value.forEach { therapist ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onTherapistClick(therapist) }
                    .padding(horizontal = 16.dp), // Added horizontal padding for better UI
            ) {
                Text(
                    text = therapist.name,
                    style = MaterialTheme.typography.bodyLarge // Use appropriate text style here
                )
            }
        }
    }
}

class TherapistRepository {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getTherapists(): List<Therapist> {
        return try {
            val snapshot = firestore.collection("therapists").get().await()
            snapshot.toObjects(Therapist::class.java)
        } catch (e: Exception) {
            // Handle exceptions (e.g., log, report, or return empty list)
            emptyList()
        }
    }

    suspend fun getTherapistById(therapistId: String): Therapist? {
        return try {
            val snapshot = firestore.collection("therapists").document(therapistId).get().await()
            snapshot.toObject(Therapist::class.java)
        } catch (e: Exception) {
            // Handle exceptions (e.g., log, report, or return null)
            null
        }
    }

    // Additional methods can be added for CRUD operations as needed

}