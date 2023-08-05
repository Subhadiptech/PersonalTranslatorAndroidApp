package com.ersubhadip.mypersonaltranslator.core

import com.ersubhadip.mypersonaltranslator.presentation.home.TextToVoiceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val globalModules = module {
    viewModel {
        TextToVoiceViewModel()
    }
}