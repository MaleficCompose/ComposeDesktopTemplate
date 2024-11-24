import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import screens.App1
import screens.App2
import xyz.malefic.extensions.get
import xyz.malefic.navigate.RouteManager
import xyz.malefic.navigate.RouteManager.RoutedNavHost
import xyz.malefic.navigate.RouteManager.RoutedSidebar
import xyz.malefic.navigate.RouteManager.navigator
import xyz.malefic.wrap.MaleficWindow

fun main() = application {
  MaleficWindow(onCloseRequest = ::exitApplication) {
    MaterialTheme {
      RouteManager.initialize(composableMap, this::class.java.getResourceAsStream("/routes.yaml")!!)
      NavigationMenu()
    }
  }
}

@Composable
fun NavigationMenu() {
  Row(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
    RoutedSidebar()
    Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))
    RoutedNavHost()
  }
}

val composableMap: Map<String, @Composable (List<String?>) -> Unit> =
  mapOf(
    "App1" to { params -> App1(id = params[0]!!, name = params[1, null]) },
    "App2" to { _ -> App2(navigator) },
    "Text" to { params -> Text(text = params[0, "Nope."]!!) },
  )
