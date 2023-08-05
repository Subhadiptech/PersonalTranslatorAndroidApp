package com.ersubhadip.mypersonaltranslator.presentation.translator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huawei.hms.mlsdk.translate.MLTranslateLanguage
import com.huawei.hms.mlsdk.translate.MLTranslatorFactory
import com.huawei.hms.mlsdk.translate.cloud.MLRemoteTranslateSetting
import com.huawei.hms.mlsdk.translate.cloud.MLRemoteTranslator

class TextTranslatorViewModel : ViewModel() {
    private lateinit var mlRemoteTranslator: MLRemoteTranslator

    private var output: MutableLiveData<String> = MutableLiveData()
    private var failureOutput: MutableLiveData<Exception> = MutableLiveData()

    var supportedLanguages: MutableLiveData<Set<String>> = MutableLiveData()

    private var loadingProgress: MutableState<Boolean> = mutableStateOf(false)
    var showLanguagesState: MutableState<Boolean> = mutableStateOf(false)
        private set
    var showTranslation: MutableState<Boolean> = mutableStateOf(false)
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
}