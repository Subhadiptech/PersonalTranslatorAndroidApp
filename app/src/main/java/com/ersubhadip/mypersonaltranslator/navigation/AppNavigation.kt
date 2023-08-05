package com.ersubhadip.mypersonaltranslator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ersubhadip.mypersonaltranslator.presentation.splash.SplashScreenComponent

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.Splash.route
) {
    val startDestinationRoute = rememberSaveable { mutableStateOf(startDestination) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute != startDestinationRoute.value) {
        startDestinationRoute.value = currentRoute ?: startDestination
    }

    NavHost(
        navController = navController,
        startDestination = startDestinationRoute.value
    ) {
        composable(route = Destinations.Splash.route) { SplashScreenComponent(navController) }
        composable(route = Destinations.Home.route) {}
    }
}