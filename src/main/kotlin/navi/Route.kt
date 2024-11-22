package navi

import androidx.compose.runtime.Composable

interface Route {
  val name: String
  val composable: @Composable (List<String?>) -> Unit
  val hidden: Boolean
}

data class DynamicRoute(
  override val name: String,
  override val composable: @Composable (List<String?>) -> Unit,
  override val hidden: Boolean,
  val params: List<String> = emptyList(),
) : Route {
  val fullName: String
    get() {
      val postfixed = params.map { if (it.endsWith("?")) "${it.dropLast(1)}}?" else "$it}" }
      return "$name/${postfixed.joinToString("/{", prefix = "{")}"
    }
}

data class StaticRoute(
  override val name: String,
  override val composable: @Composable (List<String?>) -> Unit,
  override val hidden: Boolean,
) : Route
