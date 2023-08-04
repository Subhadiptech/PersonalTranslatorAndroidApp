package com.ersubhadip.mypersonaltranslator.presentation.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ersubhadip.mypersonaltranslator.navigation.Destinations
import com.ersubhadip.mypersonaltranslator.presentation.splash.SplashScreenComponent
import com.ersubhadip.mypersonaltranslator.ui.theme.MyPersonalTranslatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPersonalTranslatorTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Destinations.Splash.route) {
                    composable(route = Destinations.Splash.route) {
                        SplashScreenComponent(navController)
                    }
                    composable(route = Destinations.Home.route) {
                    }
                }
            }
        }
    }
}