package com.ersubhadip.mypersonaltranslator.presentation.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ersubhadip.mypersonaltranslator.navigation.AppNavigation
import com.ersubhadip.mypersonaltranslator.ui.theme.MyPersonalTranslatorTheme
import com.ersubhadip.mypersonaltranslator.ui.theme.Transparent
import com.ersubhadip.mypersonaltranslator.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(White)
            MyPersonalTranslatorTheme {
                AppNavigation()
            }
        }
    }
}