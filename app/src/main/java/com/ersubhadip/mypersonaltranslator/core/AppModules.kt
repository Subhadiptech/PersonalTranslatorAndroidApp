package com.ersubhadip.mypersonaltranslator.core

import com.ersubhadip.mypersonaltranslator.presentation.translator.TranslatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val globalModules = module {
    viewModel {
        TranslatorViewModel()
    }
}