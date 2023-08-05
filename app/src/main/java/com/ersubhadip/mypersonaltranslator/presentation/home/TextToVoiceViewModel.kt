package com.ersubhadip.mypersonaltranslator.presentation.home

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale

class TextToVoiceViewModel : ViewModel() {

    private var textToSpeech: TextToSpeech? = null
    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess


    fun textToSpeech(text: String, lang: Locale, context: Context) {
        textToSpeech = TextToSpeech(
            context
        ) { status ->
            when (status) {
                TextToSpeech.SUCCESS -> {
                    textToSpeech?.let { txtToSpeech ->
                        txtToSpeech.language = lang
                        txtToSpeech.setSpeechRate(1.0f)
                        txtToSpeech.speak(
                            text,
                            TextToSpeech.QUEUE_ADD,
                            null,
                            null
                        )
                    }
                }

                TextToSpeech.ERROR -> {

                }
            }
        }
    }
}