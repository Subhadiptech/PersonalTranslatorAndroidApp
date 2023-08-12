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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ersubhadip.mypersonaltranslator.R
import com.ersubhadip.mypersonaltranslator.navigation.Destinations
import com.ersubhadip.mypersonaltranslator.ui.theme.Black
import com.ersubhadip.mypersonaltranslator.ui.theme.LexendDecaSemiBold
import com.ersubhadip.mypersonaltranslator.ui.theme.White
import com.ersubhadip.mypersonaltranslator.utilities.showToast
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(nav: NavHostController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    val context = LocalContext.current
    val speechToText = remember {
        mutableStateOf("")
    }
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
        )
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hi-IN,bn-IN")
        putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Something")
    }

    val launchActivityForResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            speechToText.value =
                result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)
                    .toString()
            context.showToast("Recorded Successfully")
            nav.navigate(
                Destinations.Translator.withArgs(speechToText.value)
            )
        } else {
            context.showToast("Something Went Wrong!")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                .clickable { launchActivityForResult.launch(intent) },
            painter = painterResource(id = R.drawable.ic_mic),
            contentDescription = null
        )
    }
}
