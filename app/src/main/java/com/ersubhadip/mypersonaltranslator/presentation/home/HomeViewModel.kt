package com.ersubhadip.mypersonaltranslator.presentation.home

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class HomeViewModel : ViewModel() {

    private var textToSpeech: TextToSpeech? = null
    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    fun getLanguages(): List<String> = listOf(
        "English",
        "English(US)",
        "English(UK)",
        "Canada",
        "French",
        "German",
        "Spanish",
        "Chinese",
        "Simplified Chinese",
        "Italian",
        "Japanese",
        "Korean"
    )

    fun textToSpeech(text: String, lang: Locale, context: Context) {
        textToSpeech = TextToSpeech(
            context
        ) { status ->
            when (status) {
                TextToSpeech.SUCCESS -> {
                    viewModelScope.launch { _isSuccess.emit(true) }
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
                    viewModelScope.launch { _isSuccess.emit(false) }
                }
            }
        }
    }
}