package com.christophprenissl.udpchat.presentation.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.christophprenissl.udpchat.presentation.util.Strings

@Composable
fun ChatScreen(onEvent: (String) -> Unit = {}) {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Row {
                Button(onClick = { onEvent("Hello World") }) {
                    Text(text = Strings.SEND_BUTTON_LABEL)
                }
            }
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}