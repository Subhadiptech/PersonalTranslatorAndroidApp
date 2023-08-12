package com.ersubhadip.mypersonaltranslator.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ersubhadip.mypersonaltranslator.service.LanguageSupportBroadcast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    languageSupportBroadcast: LanguageSupportBroadcast
) : ViewModel() {


    private val _languagesAvailable = MutableStateFlow<ArrayList<String>?>(arrayListOf())
    val languagesAvailable: StateFlow<ArrayList<String>?> = _languagesAvailable

    init {
        viewModelScope.launch { _languagesAvailable.emit(languageSupportBroadcast.mLanguages) }
    }
}