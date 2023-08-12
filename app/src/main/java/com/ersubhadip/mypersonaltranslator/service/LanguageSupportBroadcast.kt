package com.ersubhadip.mypersonaltranslator.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent


class LanguageSupportBroadcast : BroadcastReceiver() {

    var mLanguages: ArrayList<String>? = arrayListOf()
    override fun onReceive(context: Context?, intent: Intent?) {
        val extras = getResultExtras(true)
        mLanguages = extras.getStringArrayList(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES)
    }
}