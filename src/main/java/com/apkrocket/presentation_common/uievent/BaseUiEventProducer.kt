package com.apkrocket.presentation_common.uievent

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow


class BaseUiEventProducer<U : UiEvent> : UiEventProducer<U> {

    private val _uiEventFlow = MutableSharedFlow<U>()
    override val uiEventFlow: Flow<U> = _uiEventFlow

    @SuppressLint("RestrictedApi")
    override fun postUiEvent(uiEvent: U) {
        ArchTaskExecutor.getMainThreadExecutor().execute {
            _uiEventFlow.tryEmit(uiEvent)
        }
    }

}