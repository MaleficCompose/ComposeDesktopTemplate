import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.util.*
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

object Routes {
  val HOME: Pair<String, @Composable () -> Unit> = "home" to { App() }
  val HOME2: Pair<String, @Composable () -> Unit> = "home2" to { App2() }
  @HideRoute val HIDDEN: Pair<String, @Composable () -> Unit> = "hidden" to { Text("Hidden Route") }

  fun getAllRoutes(): List<Pair<String, String>> {
    return this::class
      .memberProperties
      .filter { it.returnType.classifier == Pair::class }
      .map { property ->
        val route = property.getter.call(this) as Pair<String, @Composable () -> Unit>
        route.first to
          property.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
          }
      }
  }

  fun getNonHiddenRoutes(): List<Pair<String, String>> {
    return this::class
      .memberProperties
      .filter { it.returnType.classifier == Pair::class }
      .filter { it.findAnnotation<HideRoute>() == null }
      .map { property ->
        val route = property.getter.call(this) as Pair<String, @Composable () -> Unit>
        route.first to
          property.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
          }
      }
  }

  fun getComposable(route: String): @Composable () -> Unit {
    return this::class
      .memberProperties
      .filter { it.returnType.classifier == Pair::class }
      .map { property -> property.getter.call(this) as Pair<String, @Composable () -> Unit> }
      .firstOrNull { it.first == route }
      ?.second ?: @Composable { Text("Unknown route") }
  }
}

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class HideRoute
