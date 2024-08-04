package com.christophprenissl.udpchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.christophprenissl.udpchat.data.repository.UdpChatRepository
import com.christophprenissl.udpchat.presentation.chat.ChatScreen
import com.christophprenissl.udpchat.presentation.chat.ChatViewModel
import com.christophprenissl.udpchat.presentation.theme.UDPChatTheme

@Suppress("UNCHECKED_CAST")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UDPChatTheme {
                val viewModel: ChatViewModel by viewModels<ChatViewModel> { object  : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
                            return ChatViewModel(UdpChatRepository()) as T
                        }
                        throw IllegalArgumentException("Unknown ViewModel class")
                    }
                } }
                val state = viewModel.state.collectAsStateWithLifecycle()
                ChatScreen(state = state.value, onEvent = viewModel::onEvent)
            }
        }
    }
}