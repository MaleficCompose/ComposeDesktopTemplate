import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun App2(navi: Navigator) {
  var text by remember { mutableStateOf("Hello, World 2!") }

  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      Button(onClick = { text = "Hello, Desktop 2!" }) { Text(text) }
      Spacer(modifier = Modifier.height(16.dp))
      Button(onClick = { navi.navigate("home/1061844") }) { Text("Go to App1") }
      Spacer(modifier = Modifier.height(16.dp))
      Button(onClick = { navi.navigate("hidden/boo!") }) { Text("Go to Hidden Page") }
    }
  }
}
