package com.apkrocket.presentation_common.utils

import timber.log.Timber
import java.text.NumberFormat
import java.util.*

fun String.orNull(): String? {
    return if (this.isEmpty()) null else this
}

fun Int.toDollarsAndCents(): String {
    val format = NumberFormat.getCurrencyInstance().apply {
        currency = Currency.getInstance(Locale.US)
    }

    val cents = this.toBigDecimal().movePointLeft(2)

    return try {
        format.format(cents)
    } catch (e: Exception) {
        Timber.e(e)
        "\$XX.XX"
    }
}

fun String.disallowWhiteSpace(): String {
    return this.replace(" ", "").replace("  ", "")
}