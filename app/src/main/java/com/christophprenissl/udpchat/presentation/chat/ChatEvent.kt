package com.christophprenissl.udpchat.presentation.chat

sealed interface ChatEvent {
    data object SendMessage : ChatEvent
    data class InputChanged(val input: String) : ChatEvent
}
