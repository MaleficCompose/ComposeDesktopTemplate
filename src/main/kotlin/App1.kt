import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App1(id: String, name: String?) {
  var text by remember { mutableStateOf("Hello, World!") }
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      Button(onClick = { text = "Hello, Desktop!" }) { Text(text) }
      Spacer(modifier = Modifier.height(16.dp))
      Text("ID: $id")
      name?.let { Text("Name: $name") } ?: run { Text("Unnamed") }
    }
  }
}
