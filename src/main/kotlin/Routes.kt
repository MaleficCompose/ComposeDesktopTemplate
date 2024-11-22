import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.io.InputStream
import org.yaml.snakeyaml.Yaml

data class Route(
  val name: String,
  val composable: @Composable (List<String>) -> Unit,
  val hidden: Boolean,
  val params: List<String> = emptyList(),
) {
  val dynamic: Boolean
    get() = params.isNotEmpty()
}

object Routes {
  private val routes: MutableList<Route> = mutableListOf()

  init {
    loadRoutesFromYaml()
  }

  private fun getComposableByName(composableName: String): @Composable (List<String>) -> Unit {
    return when (composableName) {
      "App" -> { params -> App(*params.toTypedArray()) }
      "App2" -> { _ -> App2() }
      "Text" -> { _ -> Text("Hidden Route") }
      else -> { _ -> Text("Unknown route") }
    }
  }

  private fun loadRoutesFromYaml() {
    val yaml = Yaml()
    val inputStream: InputStream? = this::class.java.getResourceAsStream("/routes.yaml")
    val data: Map<String, List<Map<String, Any>>> = yaml.load(inputStream)

    data["routes"]?.forEach { route ->
      val name = route["name"] as String
      val composableName = route["composable"] as String
      val composable = getComposableByName(composableName)
      val hidden = route["hidden"] == true
      val params = route["params"] as? List<String> ?: emptyList()
      routes.add(Route(name, composable, hidden, params))
    }
  }

  fun getAllRoutes(): List<String> {
    return routes.map { it.name }
  }

  fun getNonHiddenRoutes(): List<String> {
    return routes.filter { !it.hidden }.map { it.name }
  }

  fun getRouteByName(name: String): Route {
    return routes.first { it.name == name }
  }
}

fun getComposable(route: Route, params: List<String>): @Composable () -> Unit {
  return if (route.dynamic) {
    { route.composable(params) }
  } else {
    { route.composable(emptyList()) }
  }
}
