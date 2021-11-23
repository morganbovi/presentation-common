package com.apkrocket.presentation_common

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apkrocket.presentation_common.navigation.NavRoute
import com.apkrocket.presentation_common.navigation.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

open class BaseViewModel<state : UiState>(defaultState: state) : ViewModel(), KoinComponent {

    private val scope = viewModelScope

    private val _uiStateFlow = MutableStateFlow(defaultState)
    val uiStateFlow: StateFlow<state> = _uiStateFlow

    val uiState: state get() = uiStateFlow.value

    protected val navigator: Navigator by inject()

    init {
        runCatching { Timber.d("Initial UiState: $defaultState") }
    }

    protected fun updateUiState(update: (state) -> state) {
        launchCoroutine {
            withContext(Dispatchers.Main) {
                _uiStateFlow.value.run {
                    val updatedState = update(this)
                    if (uiState != updatedState) {
                        runCatching { Timber.d("UiState: $updatedState") }
                        _uiStateFlow.value = updatedState
                    }
                }
            }
        }
    }

    protected fun navigate(navRoute: NavRoute) {
        Timber.d("Navigating to: $navRoute")
        launchCoroutine {
            navigator.navigate(navRoute.route)
        }
    }

    open fun onViewStarted(arguments: Bundle? = null) {}

    @CallSuper
    open fun onError(throwable: Throwable) {
        Timber.e(throwable)
    }

    protected fun launchCoroutine(onError: ((error: Throwable) -> Unit)? = null, suspendMethod: suspend () -> Unit) {
        scope.launch {
            withContext(Dispatchers.Default) {
                try {
                    suspendMethod.invoke()
                } catch (e: Exception) {
                    onError?.invoke(e) ?: onError(e)
                }
            }
        }
    }

    protected fun <T> Flow<T>.collectFlow(action: suspend (value: T) -> Unit) {
        scope.launch {
            try {
                this@collectFlow
                    .handleError()
                    .collect(action)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun <T> Flow<T>.handleError(): Flow<T> =
        catch { e -> onError(e) }

}