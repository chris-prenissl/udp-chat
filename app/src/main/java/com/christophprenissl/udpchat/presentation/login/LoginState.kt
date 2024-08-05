package com.christophprenissl.udpchat.presentation.login

data class LoginState(
    val destinationIpAddressInput: String = "",
    val sendPortInput: String = "",
    val receivePortInput: String = "",
    val isValidInput: Boolean = true,
)