package com.apkrocket.presentation_common.snackbar

import kotlinx.coroutines.flow.Flow
import java.util.*

interface AppSnackBarProvider {
    val snackBarFlow: Flow<SnackBarUiState>

    suspend fun showSnackBar(message: String)
//fuck
    data class SnackBarUiState(
        val message: String,
        val uuid: String = UUID.randomUUID().toString()
    )
}

