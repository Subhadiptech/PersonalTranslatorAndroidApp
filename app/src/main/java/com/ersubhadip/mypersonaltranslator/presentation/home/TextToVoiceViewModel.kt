package com.ersubhadip.mypersonaltranslator.presentation.home

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.ViewModel
import java.util.Locale

class TextToVoiceViewModel : ViewModel() {

    private var textToSpeech: TextToSpeech? = null
    fun textToSpeech(text: String, lang: Locale, context: Context) {
        textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.US
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
        }
    }
}