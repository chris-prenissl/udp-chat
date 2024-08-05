package com.christophprenissl.udpchat.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.udpchat.data.model.toEntity
import com.christophprenissl.udpchat.data.repository.UdpChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: UdpChatRepository) : ViewModel() {
    init {
        viewModelScope.launch {
            repository.message().collect { messageDto ->
                val message = messageDto.toEntity()
                _state.update { state ->
                    state.copy(messages = state.messages + message)
                }
            }
        }
    }

    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()

    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.InputChanged -> _state.update { it.copy(input = event.input) }
            is ChatEvent.SendMessage ->
                viewModelScope.launch {
                    repository.sendMessage(_state.value.input)
                    _state.update { it.copy(input = "") }
                }
        }
    }
}