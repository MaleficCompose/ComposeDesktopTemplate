import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import screens.App1
import screens.App2
import xyz.malefic.components.precompose.NavWindow
import xyz.malefic.extensions.standard.get
import xyz.malefic.navigate.RouteManager
import xyz.malefic.navigate.RouteManager.RoutedNavHost
import xyz.malefic.navigate.RouteManager.RoutedSidebar
import xyz.malefic.navigate.RouteManager.navi
import xyz.malefic.navigate.config.JsonConfigLoader
import xyz.malefic.theme.MaleficTheme
import xyz.malefic.theme.util.loadThemeFromJson

/**
 * The main entry point of the application. It sets up the main window and applies the appropriate
 * theme based on the system's theme.
 */
fun main() = application {
  NavWindow(onCloseRequest = ::exitApplication) {
    // Determine the theme file path based on the system's theme (dark or light)
    val themeInputStream =
      javaClass.getResourceAsStream(
        if (isSystemInDarkTheme()) "/theme/dark.json" else "/theme/light.json"
      ) ?: throw IllegalArgumentException("Theme file not found")

    // Apply the selected theme and initialize the route manager
    MaleficTheme(loadThemeFromJson(themeInputStream)) {
      RouteManager.initialize(
        composableMap,
        this::class.java.getResourceAsStream("/routes.json")!!,
        JsonConfigLoader(),
      )
      NavigationMenu()
    }
  }
}

/**
 * Composable function that defines the navigation menu layout. It includes a sidebar and a main
 * content area separated by a divider.
 */
@Composable
fun NavigationMenu() {
  Row(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
    RoutedSidebar()
    Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))
    RoutedNavHost()
  }
}

/**
 * A map of composable functions used for routing. Each entry maps a route name to a composable
 * function that takes a list of parameters.
 */
val composableMap: Map<String, @Composable (List<String?>) -> Unit> =
  mapOf(
    "App1" to { params -> App1(id = params[0]!!, name = params[1, null]) },
    "App2" to { _ -> App2(navi) },
    "Text" to { params -> Text(text = params[0, "Nope."]) },
  )
