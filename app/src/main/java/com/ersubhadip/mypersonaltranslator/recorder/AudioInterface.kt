package com.ersubhadip.mypersonaltranslator.recorder

import java.io.File

interface AudioInterface {
    fun start(outputFile: File)
    fun stop()
}