package com.ersubhadip.mypersonaltranslator.core

import com.ersubhadip.mypersonaltranslator.presentation.home.HomeViewModel
import com.ersubhadip.mypersonaltranslator.presentation.translator.TranslatorViewModel
import com.ersubhadip.mypersonaltranslator.service.LanguageSupportBroadcast
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val globalModules = module {
    single { LanguageSupportBroadcast() }
    viewModel {
        HomeViewModel(get())
        TranslatorViewModel()
    }
}