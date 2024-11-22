import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.ProvidePreComposeLocals
import moe.tlaster.precompose.navigation.rememberNavigator
import navi.NavigationMenu
import navi.Routes.navi

fun main() = application {
  Window(onCloseRequest = ::exitApplication) {
    ProvidePreComposeLocals {
      PreComposeApp {
        MaterialTheme {
          navi = rememberNavigator()
          NavigationMenu(navi)
        }
      }
    }
  }
}

val composableMap: Map<String, @Composable (List<String?>) -> Unit> = mapOf(
  "App1" to { params -> App1(id = params[0]!!, name = params.getOrNull(1)) },
  "App2" to { _ -> App2(navi) },
  "Text" to { params -> Text(text = params.getOrNull(0) ?: "Nope.") }
)
