package com.apkrocket.presentation_common.utils

fun <T> List<T>.getMiddleItemOrNull(): T? {
    return getOrNull(size / 2)
}