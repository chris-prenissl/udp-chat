package com.christophprenissl.udpchat.presentation.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun ChatScreen(
    state: ChatState = ChatState(),
    onEvent: (ChatEvent) -> Unit = {}
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn {
                items(state.messages) {
                    Row {
                        Text(text = it.sender, style = MaterialTheme.typography.labelLarge)
                        Text(text = it.message)
                    }
                }
            }
            Row(modifier = Modifier.padding(Sizes.STANDARD_PADDING.dp)) {
                TextField(
                    value = state.input,
                    onValueChange = { onEvent(ChatEvent.InputChanged(it)) },
                    modifier = Modifier.padding(end = Sizes.STANDARD_PADDING.dp),
                )
                Button(onClick = { onEvent(ChatEvent.SendMessage) }) {
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