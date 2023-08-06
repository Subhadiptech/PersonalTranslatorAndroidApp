package com.ersubhadip.mypersonaltranslator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ersubhadip.mypersonaltranslator.presentation.home.HomeScreen
import com.ersubhadip.mypersonaltranslator.presentation.splash.SplashScreen
import com.ersubhadip.mypersonaltranslator.presentation.translator.TranslatorScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Destinations.Splash.route) { SplashScreen(navController) }
        composable(route = Destinations.Home.route) { HomeScreen(navController) }
        composable(
            route = Destinations.Translator.route + "/{data}",
            arguments = listOf(
                navArgument("data") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { navBackStack ->
            val data = navBackStack.arguments?.getString("data")
            TranslatorScreen(data = data ?: return@composable)
        }
    }
}