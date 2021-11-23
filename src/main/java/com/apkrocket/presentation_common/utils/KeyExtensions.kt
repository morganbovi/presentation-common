package com.apkrocket.presentation_common.utils

import androidx.compose.ui.input.key.*

fun KeyEvent.isKey(key: Key): Boolean {
    return type == KeyEventType.KeyUp && this.key == key
}