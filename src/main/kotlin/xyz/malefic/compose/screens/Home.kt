package xyz.malefic.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator
import xyz.malefic.compose.comps.box.BackgroundBox
import xyz.malefic.ext.string.either

@Composable
fun Home(navi: Navigator) {
    var text by remember { mutableStateOf("Hello, World 2!") }

    BackgroundBox(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TextButton(onClick = { text = text.either("Hello, World 2!", "Hello, Desktop 2!") }) { Text(text) }
            Spacer(Modifier.height(16.dp))
            TextButton(onClick = { navi.navigate("app1/123456") }) { Text("Go to App1") }
            Spacer(Modifier.height(16.dp))
            TextButton(onClick = { navi.navigate("app1/123456/Om Gupta") }) { Text("Go to App1 But With a Name") }
            Spacer(Modifier.height(16.dp))
            TextButton(onClick = { navi.navigate("hidden/boo!") }) { Text("Go to Hidden Page") }
        }
    }
}
