package com.wcapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.wcapp.ui.theme.WCAPPTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge mode for the current window
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            WCAPPTheme {
                // Using Material3's Scaffold component
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    // Displaying the Virtual Therapy screen
                    VirtualTherapyScreen()
                }
            }
        }
    }
}

@Composable
fun VirtualTherapyScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Virtual Therapy Screen",
        modifier = modifier,
        // Example of handling text layout result (optional)
        onTextLayout = { result ->
            // Handle text layout result if needed
        }
    )
}

@Preview(showBackground = true)
@Composable
fun VirtualTherapyScreenPreview() {
    WCAPPTheme {
        VirtualTherapyScreen(modifier = Modifier.fillMaxSize())
    }
}
