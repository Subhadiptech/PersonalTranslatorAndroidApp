package com.ersubhadip.mypersonaltranslator.presentation.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ersubhadip.mypersonaltranslator.navigation.AppNavigation
import com.ersubhadip.mypersonaltranslator.ui.theme.MyPersonalTranslatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPersonalTranslatorTheme {
                AppNavigation()
            }
        }
    }
}