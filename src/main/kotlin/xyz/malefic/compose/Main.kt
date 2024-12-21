package xyz.malefic.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import xyz.malefic.compose.comps.precompose.NavWindow
import xyz.malefic.compose.comps.text.typography.Heading1
import xyz.malefic.compose.ext.standard.get
import xyz.malefic.compose.ext.standard.inputstream.grass
import xyz.malefic.compose.nav.RouteManager
import xyz.malefic.compose.nav.RouteManager.RoutedNavHost
import xyz.malefic.compose.nav.RouteManager.RoutedSidebar
import xyz.malefic.compose.nav.RouteManager.navi
import xyz.malefic.compose.nav.config.MalefiConfigLoader
import xyz.malefic.compose.screens.App1
import xyz.malefic.compose.screens.Home
import xyz.malefic.compose.theming.MaleficTheme

/**
 * The xyz.malefic.compose.main entry point of the application. It sets up the xyz.malefic.compose.main window and applies the appropriate
 * theme based on the system's theme.
 */
fun main() =
    application {
        NavWindow(onCloseRequest = ::exitApplication) {
            // Determine the theme file path based on the system's theme (dark or light)
            val themeInputStream =
                grass(
                    if (isSystemInDarkTheme()) "/theme/dark.json" else "/theme/light.json",
                ) ?: throw IllegalArgumentException("Theme file not found")

            // Apply the selected theme and initialize the route manager
            MaleficTheme(themeInputStream) {
                RouteManager.initialize(
                    composableMap,
                    grass("/routes.mcf")!!,
                    MalefiConfigLoader(),
                )
                NavigationMenu()
            }
        }
    }

/**
 * Composable function that defines the navigation menu layout. It includes a sidebar and a xyz.malefic.compose.main
 * content area separated by a divider.
 */
@Composable
fun NavigationMenu() {
    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        Row(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            RoutedSidebar()
            Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))
            RoutedNavHost()
        }
    }
}

/**
 * A map of composable functions used for routing. Each entry maps a route name to a composable
 * function that takes a list of parameters.
 */
val composableMap: Map<String, @Composable (List<String?>) -> Unit> =
    mapOf(
        "App1" to { params -> App1(id = params[0]!!, name = params[1, null]) },
        "Home" to { _ -> Home(navi) },
        "Text" to { params -> Heading1(text = params[0, "Nope."]) },
    )
