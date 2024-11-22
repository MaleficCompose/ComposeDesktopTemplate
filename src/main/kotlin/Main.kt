import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.ProvidePreComposeLocals
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
@Preview
fun App() {
  var text by remember { mutableStateOf("Hello, World!") }

  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Button(onClick = { text = "Hello, Desktop!" }) { Text(text) }
  }
}

@Composable
fun App2() {
  var text by remember { mutableStateOf("Hello, World 2!") }

  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Button(onClick = { text = "Hello, Desktop 2!" }) { Text(text) }
  }
}

@Composable
fun NavigationMenu(navi: Navigator) {
  Row(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
    Sidebar(navi)
    Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))
    NavHost(navi, initialRoute = Routes.getNonHiddenRoutes().first()) {
      Routes.getAllRoutes().forEach { route -> scene(route) { Routes.getComposable(route)() } }
    }
  }
}

@Composable
fun Sidebar(navi: Navigator) {
  Column(
    modifier = Modifier.width(200.dp).fillMaxHeight(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Routes.getNonHiddenRoutes().forEach { route ->
      Button(onClick = { navi.navigate(route) }) { Text(route.capitalize(Locale.current)) }
    }
  }
}

fun main() = application {
  Window(onCloseRequest = ::exitApplication) {
    ProvidePreComposeLocals {
      PreComposeApp { MaterialTheme { NavigationMenu(rememberNavigator()) } }
    }
  }
}
