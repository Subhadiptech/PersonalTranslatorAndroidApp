package com.ersubhadip.mypersonaltranslator.presentation.translator

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huawei.hms.mlsdk.translate.MLTranslateLanguage
import com.huawei.hms.mlsdk.translate.MLTranslatorFactory
import com.huawei.hms.mlsdk.translate.cloud.MLRemoteTranslateSetting
import com.huawei.hms.mlsdk.translate.cloud.MLRemoteTranslator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class TranslatorViewModel : ViewModel() {

    private var textToSpeech: TextToSpeech? = null
    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    private val _translatedText = MutableStateFlow("")
    val translatedText: StateFlow<String> = _translatedText

    private lateinit var mlRemoteTranslator: MLRemoteTranslator
    private var output: MutableLiveData<String> = MutableLiveData()
    private var failureOutput: MutableLiveData<Exception> = MutableLiveData()
    private var supportedLanguages: MutableLiveData<Set<String>> = MutableLiveData()
    private var loadingProgress: MutableState<Boolean> = mutableStateOf(false)
    private var showLanguagesState: MutableState<Boolean> = mutableStateOf(false)
        private set
    private var showTranslation: MutableState<Boolean> = mutableStateOf(false)
        private set

    init {
        initializeMLRemoteTranslator("en")
    }

    private fun initializeMLRemoteTranslator(lang: String) {
        val setting = MLRemoteTranslateSetting.Factory()
            .setSourceLangCode(null)
            .setTargetLangCode(lang)
            .create()
        this.mlRemoteTranslator = MLTranslatorFactory.getInstance().getRemoteTranslator(setting)
    }

    fun getOutput(): LiveData<String> {
        return output
    }

    fun getFailureOutput(): LiveData<Exception> {
        return failureOutput
    }

    fun resetFailureOutput() {
        this.failureOutput.value = null
    }

    fun getLoadingProgress(): MutableState<Boolean> {
        return loadingProgress
    }

    private fun setLoading(loading: Boolean) {
        this.loadingProgress.value = loading
    }

    fun loadSupportedLanguages() {
        showTranslation.value = false
        setLoading(true)

        MLTranslateLanguage.getCloudAllLanguages().addOnSuccessListener {
            setLoading(false)
            supportedLanguages.value = it
            showLanguagesState.value = true
        }.addOnFailureListener {
            setLoading(false)
            failureOutput.value = it
            showLanguagesState.value = false
        }
    }

    fun translate(text: String) {
        setLoading(true)
        mlRemoteTranslator.asyncTranslate(text).addOnSuccessListener {
            setLoading(false)
            showTranslation.value = true
            output.value = it
        }.addOnFailureListener {
            setLoading(false)
            failureOutput.value = it
        }
    }

    fun getLanguages() = listOf<Pair<String, Locale>>(
        Pair("English", Locale.ENGLISH),
        Pair("English(US)", Locale.US),
        Pair("English(UK)", Locale.UK),
        Pair("Canada", Locale.CANADA),
        Pair("French", Locale.FRENCH),
        Pair("German", Locale.GERMAN),
        Pair("Chinese", Locale.CHINESE),
        Pair("Simplified Chinese", Locale.SIMPLIFIED_CHINESE),
        Pair("Italian", Locale.ITALIAN),
        Pair("Japanese", Locale.JAPANESE),
        Pair("Korean", Locale.KOREAN)
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