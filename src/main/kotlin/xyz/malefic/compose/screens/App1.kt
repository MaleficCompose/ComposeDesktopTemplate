package xyz.malefic.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.malefic.compose.comps.box.BackgroundBox
import xyz.malefic.compose.comps.text.typography.Heading2
import xyz.malefic.ext.string.either

@Composable
fun App1(
    id: String,
    name: String?,
) {
    var text by remember { mutableStateOf("Hello, World!") }

    BackgroundBox(contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(onClick = { text = text.either("Hello, World!", "Hello, Desktop!") }) { Text(text) }
            Spacer(modifier = Modifier.height(16.dp))
            Heading2("ID: $id")
            name?.let { Heading2("Name: $name") } ?: run { Heading2("Unnamed") }
        }
    }
}
