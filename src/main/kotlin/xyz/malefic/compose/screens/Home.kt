package xyz.malefic.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
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
import xyz.malefic.compose.comps.text.typography.Body1
import xyz.malefic.ext.precompose.gate
import xyz.malefic.ext.string.either

@Composable
fun Home(navi: Navigator) {
    var text by remember { mutableStateOf("Hello, World 2!") }
    var name by remember { mutableStateOf("") }

    BackgroundBox(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TextButton(onClick = { text = text.either("Hello, World 2!", "Hello, Desktop 2!") }) { Text(text) }
            Spacer(Modifier.height(16.dp))
            if (name.isEmpty()) {
                TextButton(onClick = { navi gate "app1/123" }) { Text("Go to App1") }
            } else {
                TextButton(onClick = { navi gate "app1/123/$name" }) { Text("Go to App1 But With $name") }
            }
            Spacer(Modifier.height(16.dp))
            TextButton(onClick = { navi gate "hidden/boo!" }) { Text("Go to Hidden Page") }
            Spacer(Modifier.height(16.dp))
            TextField(value = name, onValueChange = { name = it }, placeholder = { Body1(text = "Type Name Here!") })
        }
    }
}
