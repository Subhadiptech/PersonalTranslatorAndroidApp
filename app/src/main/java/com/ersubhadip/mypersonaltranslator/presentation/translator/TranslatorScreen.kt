package com.ersubhadip.mypersonaltranslator.presentation.translator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ersubhadip.mypersonaltranslator.ui.theme.AlegreyaSansRegular
import com.ersubhadip.mypersonaltranslator.ui.theme.Black
import com.ersubhadip.mypersonaltranslator.ui.theme.LexendDecaSemiBold
import com.ersubhadip.mypersonaltranslator.ui.theme.Orange
import com.ersubhadip.mypersonaltranslator.ui.theme.White
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TranslatorScreen(data: String) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val viewModel: TranslatorViewModel by inject(TranslatorViewModel::class.java)

    val translationSuccessful by viewModel.isSuccess.collectAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetElevation = 2.dp,
        sheetBackgroundColor = White,
        sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        sheetContent = {
            BottomSheetContent(viewModel, data)
        }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { scope.launch { sheetState.show() } }) {
                Text(text = "Speak my language")
            }
        }
    }
}

@Composable
fun BottomSheetContent(viewModel: TranslatorViewModel, text: String) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Select language you understand",
            textAlign = TextAlign.Center,
            fontFamily = LexendDecaSemiBold,
            color = Black,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow {
            items(viewModel.getLanguages()) {
                Text(
                    text = it.first,
                    fontFamily = AlegreyaSansRegular,
                    color = White,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clip(
                            RoundedCornerShape(8.dp)
                        )
                        .background(Orange)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                        .clickable {
                            viewModel.textToSpeech(
                                text = text,
                                lang = it.second,
                                context = context
                            )
                        }
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}