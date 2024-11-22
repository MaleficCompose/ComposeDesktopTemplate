package navi

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.io.InputStream
import moe.tlaster.precompose.navigation.Navigator
import org.yaml.snakeyaml.Yaml
import composableMap

object Routes {
  val dynamicRoutes: MutableList<DynamicRoute> = mutableListOf()
  val staticRoutes: MutableList<StaticRoute> = mutableListOf()
  private val allRoutes: List<Route>
    get() = staticRoutes + dynamicRoutes

  lateinit var navi: Navigator
  private var startupRoute: String? = null

  init {
    loadRoutesFromYaml()
  }

  private fun getComposableByName(composableName: String): @Composable (List<String?>) -> Unit =
    composableMap[composableName] ?: { _ -> Text("Unknown route") }

  private fun loadRoutesFromYaml() {
    val yaml = Yaml()
    val inputStream: InputStream? = this::class.java.getResourceAsStream("/routes.yaml")
    val data: Map<String, Any> = yaml.load(inputStream)

    loadData(data)

    startupRoute = data["startup"] as? String
    println("Startup Route: $startupRoute")
  }

  @Suppress("UNCHECKED_CAST")
  private fun loadData(data: Map<String, Any>) {
    data["routes"]?.let { routeList ->
      (routeList as List<Map<String, Any>>).forEach { route ->
        val name = route["name"] as String
        val composableName = route["composable"] as String
        val composable = getComposableByName(composableName)
        val hidden = route["hidden"] == true
        val params = route["params"] as? List<String>
        params?.let { dynamicRoutes.add(DynamicRoute(name, composable, hidden, it)) }
          ?: staticRoutes.add(StaticRoute(name, composable, hidden))
        println("Loaded route: $name with params: $params")
      }
    }
  }

  fun getNonHiddenRoutes() = allRoutes.filter { !it.hidden }

  fun getStartupRouteName() = startupRoute ?: getNonHiddenRoutes().first().name
}
