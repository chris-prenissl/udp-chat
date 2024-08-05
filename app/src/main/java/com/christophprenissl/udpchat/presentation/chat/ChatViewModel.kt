package com.christophprenissl.udpchat.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.udpchat.data.model.toEntity
import com.christophprenissl.udpchat.data.repository.UdpChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: UdpChatRepository) : ViewModel() {
    private val _message = repository.message()
        .map { it.toEntity() }

    private val _state = MutableStateFlow(ChatState())
    val state = combine(_state, _message) { state, message->
        state.copy(messages = state.messages + message)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        ChatState()
    )

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