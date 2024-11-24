pluginManagement {
  repositories {
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    gradlePluginPortal()
    mavenCentral()
  }

  plugins {
    kotlin("jvm").version(extra["kotlin.version"] as String)
    id("org.jetbrains.compose").version(extra["compose.version"] as String)
    id("org.jetbrains.kotlin.plugin.compose").version(extra["kotlin.version"] as String)
  }
}

rootProject.name = "ComposeDesktopTemplate"

includeBuild("C:/Users/Malefic/Documents/Programming/Libs/MaleficNav") {
  dependencySubstitution {
    substitute(module("xyz.malefic:maleficnav")).using(project(":"))
  }
}
