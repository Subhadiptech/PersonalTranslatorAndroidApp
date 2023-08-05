package com.ersubhadip.mypersonaltranslator.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ersubhadip.mypersonaltranslator.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun HomeScreen(nav: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)
}