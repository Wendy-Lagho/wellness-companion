// ChatComponents.kt

package com.wcapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcapp.ui.theme.WCAPPTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                MessagesList(messages = listOf("Hello!", "How can I help you today?"))

                Spacer(modifier = Modifier.height(16.dp))

                ChatInput(onSendMessage = { /* Handle sending message */ })
            }
        }
    )
}

@Composable
fun MessagesList(messages: List<String>) {
    Column {
        messages.forEach { message ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = message,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun ChatInput(onSendMessage: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Type your message:",
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = { /* Implement sending message */ },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    WCAPPTheme {
        ChatScreen()
    }
}
