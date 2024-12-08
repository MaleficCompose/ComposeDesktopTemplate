package screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.malefic.components.box.BackgroundBox
import xyz.malefic.components.text.typography.Heading2
import xyz.malefic.extensions.standard.string.either

@Composable
fun App1(id: String, name: String?) {
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
