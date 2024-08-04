package com.christophprenissl.udpchat.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.udpchat.data.repository.UdpChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: UdpChatRepository) : ViewModel() {

    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()

    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.InputChanged -> _state.update { _state.value.copy(input = event.input) }
            is ChatEvent.SendMessage ->
                viewModelScope.launch {
                    repository.sendMessage(state.value.input)
                    _state.update { _state.value.copy(input = "") }
                }
        }
    }
}