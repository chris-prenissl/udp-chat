package com.christophprenissl.udpchat.presentation.chat

import com.christophprenissl.udpchat.domain.model.Message

data class ChatState(val input: String = "", val messages: List<Message> = emptyList())
