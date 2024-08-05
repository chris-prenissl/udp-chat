package com.christophprenissl.udpchat.data.model

import com.christophprenissl.udpchat.domain.model.Message

data class MessageDto(val sender: String, val message: String)

fun MessageDto.toEntity(): Message {
    return Message(
        sender = sender,
        message = message
    )
}