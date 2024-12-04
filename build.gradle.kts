import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  kotlin("jvm")
  id("org.jetbrains.compose")
  id("org.jetbrains.kotlin.plugin.compose")
  alias(libs.plugins.spotless)
}

group = "xyz.malefic"
version = "1.0.0"

repositories {
  mavenCentral()
  maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  google()
}

dependencies {
  implementation(compose.desktop.currentOs)
  implementation(libs.precompose)
  implementation(libs.malefic.nav)
  implementation(libs.malefic.extensions)
  implementation(libs.malefic.components)
}

compose.desktop {
  application {
    mainClass = "MainKt"

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "ComposeDesktopTemplate"
      packageVersion = "1.0.0"
    }
  }
}

spotless {
  kotlin {
    ktfmt().googleStyle()
  }
}
