package com.ersubhadip.mypersonaltranslator.presentation.home

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.ersubhadip.mypersonaltranslator.R
import com.ersubhadip.mypersonaltranslator.ui.theme.White
import com.ersubhadip.mypersonaltranslator.utilities.showToast
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.util.Locale


@Composable
fun HomeScreen(nav: NavHostController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    val context = LocalContext.current
    var speechToText = remember {
        ""
    }

    val launchActivityForResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            speechToText =
                result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)
                    .toString()
            context.showToast("Recorded Successfully")
        } else {
            context.showToast("Something Went Wrong!")
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Something")
        Image(
            modifier = Modifier.clickable { launchActivityForResult.launch(intent) },
            painter = painterResource(id = R.drawable.ic_mic),
            contentDescription = null
        )
    }
}