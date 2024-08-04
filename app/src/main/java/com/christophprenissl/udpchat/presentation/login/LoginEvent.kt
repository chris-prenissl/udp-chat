package com.christophprenissl.udpchat.presentation.login

sealed interface LoginEvent {
    data class DestinationIpAddressInputChanged(val input: String): LoginEvent
    data class SendPortInputChanged(val input: String): LoginEvent
    data class ReceivePortInputChanged(val input: String): LoginEvent
    data object LoginPressed: LoginEvent
}