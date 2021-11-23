package com.apkrocket.presentation_common.snackbar

import com.apkrocket.presentation_common.snackbar.AppSnackBarProvider.SnackBarUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext

class AppSnackBarProviderImpl : AppSnackBarProvider {

    private val _snackBarFlow = MutableSharedFlow<SnackBarUiState>()
    override val snackBarFlow: Flow<SnackBarUiState> = _snackBarFlow

    override suspend fun showSnackBar(message: String) {
        withContext(Dispatchers.Main) {
            _snackBarFlow.emit(
                SnackBarUiState(
                    message = message
                )
            )
        }
    }
}