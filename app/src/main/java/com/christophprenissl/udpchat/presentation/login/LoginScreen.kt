package com.christophprenissl.udpchat.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.christophprenissl.udpchat.presentation.util.Sizes
import com.christophprenissl.udpchat.presentation.util.Strings

@Composable
fun LoginScreen(
    state: LoginState = LoginState(),
    onEvent: (LoginEvent) -> Unit = {},
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Text(text = Strings.LOGIN_BUTTON_LABEL, style = MaterialTheme.typography.titleLarge)
            Column {
                TextField(
                    value = state.destinationIpAddressInput,
                    onValueChange = {
                        onEvent(LoginEvent.DestinationIpAddressInputChanged(it))
                    },
                    label = { Text(text = Strings.DESTINATION_IP_ADDRESS_LABEL) },
                    modifier = Modifier.padding(Sizes.STANDARD_PADDING.dp)
                )
                TextField(
                    value = state.sendPortInput,
                    onValueChange = {
                        onEvent(LoginEvent.SendPortInputChanged(it))
                    },
                    label = { Text(text = Strings.SEND_PORT_LABEL) },
                    modifier = Modifier.padding(Sizes.STANDARD_PADDING.dp)
                )
                TextField(
                    value = state.receivePortInput,
                    onValueChange = {
                        onEvent(LoginEvent.ReceivePortInputChanged(it))
                    },
                    label = { Text(text = Strings.RECEIVE_PORT_LABEL) },
                    modifier = Modifier.padding(Sizes.STANDARD_PADDING.dp)
                )
            }
            Button(onClick = { onEvent(LoginEvent.LoginPressed) }) {
                Text(text = Strings.LOGIN_BUTTON_LABEL)
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}