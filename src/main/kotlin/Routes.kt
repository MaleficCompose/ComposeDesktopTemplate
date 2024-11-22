import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.io.InputStream
import org.yaml.snakeyaml.Yaml

data class Route(val name: String, val composable: @Composable () -> Unit, val hidden: Boolean)

object Routes {
  private val routes: MutableList<Route> = mutableListOf()

  init {
    loadRoutesFromYaml()
  }

  private fun loadRoutesFromYaml() {
    val yaml = Yaml()
    val inputStream: InputStream? = this::class.java.getResourceAsStream("/routes.yaml")
    val data: Map<String, List<Map<String, Any>>> = yaml.load(inputStream)

    data["routes"]?.forEach { route ->
      val name = route["name"] as String
      val composableName = route["composable"] as String
      val composable: @Composable () -> Unit =
        when (composableName) {
          "App" -> {
            @Composable { App() }
          }
          "App2" -> {
            @Composable { App2() }
          }
          "Text" -> {
            @Composable { Text("Hidden Route") }
          }
          else -> {
            @Composable { Text("Unknown route") }
          }
        }
      val hidden = route["hidden"] == true
      routes.add(Route(name, composable, hidden))
    }
  }

  fun getAllRoutes(): List<String> {
    return routes.map { it.name }
  }

  fun getNonHiddenRoutes(): List<String> {
    return routes.filter { !it.hidden }.map { it.name }
  }

  fun getComposable(route: String): @Composable () -> Unit {
    return routes.firstOrNull { it.name == route }?.composable
      ?: @Composable { Text("Unknown route") }
  }
}
