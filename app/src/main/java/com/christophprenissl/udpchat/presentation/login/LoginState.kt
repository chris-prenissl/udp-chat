package com.christophprenissl.udpchat.presentation.login

data class LoginState(
    val destinationIpAddressInput: String = "10.0.2.2",
    val sendPortInput: String = "12345",
    val receivePortInput: String = "54321",
    val isValidInput: Boolean = true,
)