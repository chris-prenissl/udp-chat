package com.christophprenissl.udpchat.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.udpchat.data.repository.UdpChatRepository
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: UdpChatRepository) : ViewModel() {

    fun onEvent(chatMessage: String) {
       viewModelScope.launch {
           repository.sendMessage(chatMessage, "10.0.2.2", 12345)
       }
    }
}