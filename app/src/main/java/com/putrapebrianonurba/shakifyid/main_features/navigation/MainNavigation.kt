package com.putrapebrianonurba.shakifyid.main_features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MainRoute.Main.route) {
        mainNavGraph(navController)
    }
}