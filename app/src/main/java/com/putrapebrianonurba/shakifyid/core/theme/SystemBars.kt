package com.putrapebrianonurba.shakifyid.core.theme

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.LocalActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

@Composable
fun SystemBars(
    darkTheme: Boolean
) {
    val activity = LocalActivity.current as ComponentActivity

    val backgroundColor = MaterialTheme.colorScheme.onBackground.toArgb()

    SideEffect {
        activity.enableEdgeToEdge(
            statusBarStyle =
                if (darkTheme) {
                    SystemBarStyle.dark(
                        scrim = Color.Transparent.toArgb()
                    )
                } else {
                    SystemBarStyle.light(
                        scrim = Color.Transparent.toArgb(),
                        darkScrim = backgroundColor
                    )
                },
            navigationBarStyle =
                if (darkTheme) {
                    SystemBarStyle.dark(
                        scrim = backgroundColor,
                    )
                } else {
                    SystemBarStyle.light(
                        scrim = backgroundColor,
                        darkScrim = backgroundColor
                    )
                }
        )
    }
}