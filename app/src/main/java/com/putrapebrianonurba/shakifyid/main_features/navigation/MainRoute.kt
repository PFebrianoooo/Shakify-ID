package com.putrapebrianonurba.shakifyid.main_features.navigation

sealed class MainRoute(val route: String) {
    object Main: MainRoute("Shakify")
    object FEED: MainRoute("shakify/feed")
    object SETTINGS: MainRoute("shakify/feed/settings")
}