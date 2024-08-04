package com.christophprenissl.udpchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.christophprenissl.udpchat.presentation.navigation.Navigation
import com.christophprenissl.udpchat.presentation.theme.UDPChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UDPChatTheme {
                Navigation()
            }
        }
    }
}