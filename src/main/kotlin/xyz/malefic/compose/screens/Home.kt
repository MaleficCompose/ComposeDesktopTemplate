package xyz.malefic.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator
import xyz.malefic.compose.comps.box.BackgroundBox
import xyz.malefic.compose.engine.factory.ButtonFactory
import xyz.malefic.compose.engine.factory.ColumnFactory
import xyz.malefic.compose.engine.factory.TextFactory
import xyz.malefic.compose.engine.pocket.*
import xyz.malefic.ext.string.either

@Composable
fun Home(navi: Navigator) {
    var text by remember { mutableStateOf("Hello, World 2!") }

    BackgroundBox(contentAlignment = Alignment.Center) {
        ColumnFactory {
            ButtonFactory { TextFactory(text)() }
                .apply {
                    onClick = { text = text.either("Hello, World 2!", "Hello, Desktop 2!") }
                }.compose()
                .space(16.dp)()
            ButtonFactory { TextFactory("Go to App1")() }
                .apply {
                    onClick = { navi.navigate("app1/123456") }
                }.compose()
                .space(16.dp)()
            ButtonFactory { TextFactory("Go to App1 But With a Name")() }
                .apply {
                    onClick = { navi.navigate("app1/123456/Om Gupta") }
                }.compose()
                .space(16.dp)()
            ButtonFactory { TextFactory("Go to Hidden Page")() }
                .apply {
                    onClick = { navi.navigate("hidden/boo!") }
                }.compose()()
        } *= {
            horizontalAlignment = Alignment.CenterHorizontally
            verticalArrangement = Arrangement.Center
        }
    }
}