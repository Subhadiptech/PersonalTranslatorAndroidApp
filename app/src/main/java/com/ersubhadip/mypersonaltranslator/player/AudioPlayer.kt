package com.ersubhadip.mypersonaltranslator.player

import java.io.File

interface AudioPlayer {
    fun play(file: File)
    fun stop()
}