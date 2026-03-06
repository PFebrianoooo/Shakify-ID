package com.putrapebrianonurba.shakifyid.main_features.navigation

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.putrapebrianonurba.shakifyid.main_features.presentation.EarthquakeFeedViewModel
import com.putrapebrianonurba.shakifyid.main_features.presentation.EarthquakeSettingsViewModel
import com.putrapebrianonurba.shakifyid.main_features.ui.EarthquakeFeedScreen
import com.putrapebrianonurba.shakifyid.main_features.ui.EarthquakeSettingsScreen

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    val ANIMATION_DURATION = 400

    navigation(
        startDestination = MainRoute.FEED.route,
        route = MainRoute.Main.route
    ) {
        composable(
            route = MainRoute.FEED.route,
        ) {
            val viewModel: EarthquakeFeedViewModel = hiltViewModel()

            EarthquakeFeedScreen(
                viewModel = viewModel,
                onNavigateSettings = { navController.navigate(MainRoute.SETTINGS.route) }
            )
        }

        composable(
            route = MainRoute.SETTINGS.route,
            enterTransition = {
                slideIntoContainer(
                    towards = SlideDirection.Left,
                    animationSpec = tween(ANIMATION_DURATION)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = SlideDirection.Left,
                    animationSpec = tween(ANIMATION_DURATION)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = SlideDirection.Right,
                    animationSpec = tween(ANIMATION_DURATION)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = SlideDirection.Right,
                    animationSpec = tween(ANIMATION_DURATION)
                )
            }
        ) {
            val viewModel: EarthquakeSettingsViewModel = hiltViewModel()

            EarthquakeSettingsScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}