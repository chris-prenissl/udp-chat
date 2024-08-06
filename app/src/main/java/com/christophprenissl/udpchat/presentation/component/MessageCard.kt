package com.christophprenissl.udpchat.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.christophprenissl.udpchat.domain.model.Message
import com.christophprenissl.udpchat.presentation.util.Sizes

@Composable
fun MessageCard(message: Message) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(),
        modifier = Modifier.padding(Sizes.STANDARD_PADDING.dp)
    ) {
        Text(
            text = message.sender,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = Sizes.STANDARD_PADDING.dp)
        )
        Text(text = message.message, modifier = Modifier.padding(Sizes.STANDARD_PADDING.dp))
    }
}

@Preview
@Composable
fun MessageCardPreview() {
    MessageCard(message = Message("sender", "message"))
}