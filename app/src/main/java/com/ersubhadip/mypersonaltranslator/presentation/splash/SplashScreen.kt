package com.ersubhadip.mypersonaltranslator.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ersubhadip.mypersonaltranslator.R
import com.ersubhadip.mypersonaltranslator.navigation.Destinations
import com.ersubhadip.mypersonaltranslator.ui.theme.Black
import com.ersubhadip.mypersonaltranslator.ui.theme.LexendDecaLight
import com.ersubhadip.mypersonaltranslator.ui.theme.LexendDecaSemiBold
import com.ersubhadip.mypersonaltranslator.ui.theme.Orange
import com.ersubhadip.mypersonaltranslator.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(nav: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)
    val scope = rememberCoroutineScope()
    DisposableEffect(key1 = true) {
        scope.launch {
            delay(3000L)
            nav.navigate(Destinations.Home.route)
        }

        onDispose { scope.cancel() }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_splash),
                contentDescription = "splash_icon",
                modifier = Modifier
                    .size(120.dp)

            )
            Text(
                text = "Personal Translator",
                color = Orange,
                fontFamily = LexendDecaSemiBold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Translate on the go",
                color = Black,
                fontFamily = LexendDecaLight,
                fontSize = 16.sp
            )
        }
    }
}