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
                val destinationIpAddress = _state.value.destinationIpAddressInput.trim()
                val sendPort = _state.value.sendPortInput.trim()
                val receivePort = _state.value.receivePortInput.trim()
                val isValidInput = isValidInput(destinationIpAddress, sendPort, receivePort)
                _state.update { _state.value.copy(isValidInput = isValidInput) }
                if (isValidInput) {
                    udpChatRepository.setup(
                        destinationIpAddress = destinationIpAddress,
                        sendPort = sendPort.toInt(),
                        receivePort = receivePort.toInt()
                    )
                    viewModelScope.launch {
                        _navigationEvent.send(LoginNavigationEvent.Login)
                    }
                }
            }

            LoginEvent.UserASelected -> _state.update {
                _state.value.copy(destinationIpAddressInput = "10.0.2.2", sendPortInput = "12345", receivePortInput = "54321")
            }
            LoginEvent.UserBSelected -> _state.update {
                _state.value.copy(destinationIpAddressInput = "10.0.2.2", sendPortInput = "54321", receivePortInput = "12345")
            }
        }
    }

    private fun isValidInput(destinationIpAddress: String, sendPort: String, receivePort: String): Boolean {
        val validIpAddress = destinationIpAddress.matches(Regex("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"))
        val validSendPort = sendPort.toIntOrNull() != null
        val validReceivePort = receivePort.toIntOrNull() != null
        return validIpAddress && validSendPort && validReceivePort
    }
}