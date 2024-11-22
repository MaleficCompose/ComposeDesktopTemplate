package navi

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun NavigationMenu(navi: Navigator) {
  Row(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
    Sidebar(navi)
    Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))

    val initialRoute = Routes.getStartupRouteName()
    println("Initial Route: $initialRoute")

    NavHost(navi, initialRoute = initialRoute) {
      Routes.dynamicRoutes.forEach { route ->
        println("Adding dynamic route ${route.name}")
        scene(route.fullName) { params -> route.composable(params.pathMap.values.toList()) }
      }
      Routes.staticRoutes.forEach { route ->
        println("Adding static route ${route.name}")
        scene(route.name) { route.composable(emptyList()) }
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
      Button(onClick = { navi.navigate(route.name) }) {
        Text(route.name.capitalize(Locale.current))
      }
    }
  }
}
