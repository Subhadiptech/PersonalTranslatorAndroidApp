package com.ersubhadip.mypersonaltranslator.presentation.home

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ersubhadip.mypersonaltranslator.R
import com.ersubhadip.mypersonaltranslator.ui.theme.Black
import com.ersubhadip.mypersonaltranslator.ui.theme.LexendDecaSemiBold
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
            nav.navigate("Destinations.Translator.route/$speechToText")
        } else {
            context.showToast("Something Went Wrong!")
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Record and translate",
            color = Black,
            fontFamily = LexendDecaSemiBold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(120.dp))
        Image(
            modifier = Modifier
                .size(120.dp)
                .clickable { launchActivityForResult.launch(setupSpeechToTextIntent()) },
            painter = painterResource(id = R.drawable.ic_mic),
            contentDescription = null
        )
    }
}


fun setupSpeechToTextIntent() = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)
    putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
    putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Something")
}