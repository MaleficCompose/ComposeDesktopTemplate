import Routes.navi
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
fun App1(id: String, name: String) {
  var text by remember { mutableStateOf("Hello, World!") }
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      Button(onClick = { text = "Hello, Desktop!" }) { Text(text) }
      Spacer(modifier = Modifier.height(16.dp))
      Text("ID: $id")
      Text("Name: $name")
    }
  }
}

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
      Button(onClick = { navi.navigate("home/1061844/Om") }) { Text("Go to App1") }
      Spacer(modifier = Modifier.height(16.dp))
      Button(onClick = { navi.navigate("hidden/boo!") }) { Text("Go to Hidden Page") }
    }
  }
}

@Composable
fun NavigationMenu(navi: Navigator) {
  Row(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
    Sidebar(navi)
    Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))
    NavHost(navi, initialRoute = Routes.getNonHiddenRoutes().first()) {
      Routes.getAllRoutes().forEach { routeName ->
        val route = Routes.getRouteByName(routeName)
        if (route.dynamic) {
          scene(route.name + "/{${route.params.joinToString("}/{")}}") { backStackEntry ->
            val params = route.params.map { paramName ->
              backStackEntry.pathMap[paramName] ?: ""
            }
            getComposable(route, params)()
          }
        } else {
          scene(routeName) {
            getComposable(route)()
          }
        }
      }
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
      PreComposeApp {
        MaterialTheme {
          navi = rememberNavigator()
          NavigationMenu(navi)
        }
      }
    }
  }
}
