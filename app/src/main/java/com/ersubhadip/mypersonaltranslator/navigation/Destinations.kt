package com.ersubhadip.mypersonaltranslator.navigation

sealed class Destinations(val route: String) {
    object Splash : Destinations(Routes.Splash)
    object Home : Destinations(Routes.Home)
}