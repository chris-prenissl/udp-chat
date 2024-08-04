package com.christophprenissl.udpchat.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.udpchat.data.repository.UdpChatRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val udpChatRepository: UdpChatRepository) : ViewModel() {
    private val _navigationEvent = Channel<LoginNavigationEvent>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.DestinationIpAddressInputChanged -> _state.update { _state.value.copy(destinationIpAddressInput = event.input) }
            is LoginEvent.SendPortInputChanged -> _state.update { _state.value.copy(sendPortInput = event.input) }
            is LoginEvent.ReceivePortInputChanged -> _state.update { _state.value.copy(receivePortInput = event.input) }
            is LoginEvent.LoginPressed -> {
                val isValidInput = isValidInput()
                _state.update { _state.value.copy(isValidInput = isValidInput) }
                if (isValidInput) {
                    val destinationIpAddress = _state.value.destinationIpAddressInput
                    val sendPort = _state.value.sendPortInput.toInt()
                    val receivePort = _state.value.receivePortInput.toInt()
                    udpChatRepository.setup(
                        destinationIpAddress = destinationIpAddress,
                        sendPort = sendPort,
                        receivePort = receivePort
                    )
                    viewModelScope.launch {
                        _navigationEvent.send(LoginNavigationEvent.Login)
                    }
                }
            }
        }
    }

    private fun isValidInput(): Boolean {
        val validIpAddress = _state.value.destinationIpAddressInput.matches(Regex("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"))
        val validSendPort = _state.value.sendPortInput.toIntOrNull() != null
        val validReceivePort = _state.value.receivePortInput.toIntOrNull() != null
        return validIpAddress && validSendPort && validReceivePort
    }
}