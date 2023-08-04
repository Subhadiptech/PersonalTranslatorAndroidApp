package com.ersubhadip.mypersonaltranslator.utilities

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.offset

fun spanText(startSeq: String, midSeq: String, endSeq: String = "", textColorStyle: SpanStyle, spanColorStyle: SpanStyle): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = textColorStyle) {
            append(startSeq)
        }
        withStyle(style = spanColorStyle) {
            append(midSeq)
        }
        withStyle(style = textColorStyle) {
            append(endSeq)
        }
    }
}

fun Any?.log(tag: String = "Debug_logs ->") {
    this?.let {
        Log.d(tag, it.toString())
    }
}

fun Modifier.percentagePaddingTop(percentage: Int): Modifier = this.then(
    layout { measurable, constraints ->
        val paddingTop = (constraints.maxHeight * percentage / 100).coerceAtLeast(0)
        val placeable = measurable.measure(constraints.offset(vertical = paddingTop))
        layout(placeable.width, placeable.height) {
            placeable.place(0, paddingTop)
        }
    }
)


fun Modifier.percentagePaddingBottom(percentage: Int): Modifier = this.then(
    layout { measurable, constraints ->
        val paddingBottom = (constraints.maxHeight * percentage / 100).coerceAtLeast(0)
        val placeable = measurable.measure(constraints.offset(vertical = -paddingBottom))
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
)

fun Modifier.percentagePaddingStart(percentage: Int): Modifier = this.then(
    layout { measurable, constraints ->
        val paddingStart = (constraints.maxWidth * percentage / 100).coerceAtLeast(0)
        val placeable = measurable.measure(constraints.offset(horizontal = paddingStart))
        layout(placeable.width, placeable.height) {
            placeable.place(paddingStart, 0)
        }
    }
)

fun Modifier.percentagePaddingEnd(percentage: Int): Modifier = this.then(
    layout { measurable, constraints ->
        val paddingEnd = (constraints.maxWidth * percentage / 100).coerceAtLeast(0)
        val placeable = measurable.measure(constraints.offset(horizontal = -paddingEnd))
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
)


fun Context.showToast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}