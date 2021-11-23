package com.apkrocket.presentation_common.uievent

import com.apkrocket.presentation_common.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

interface UiEventConsumer<UiEventType : UiEvent> {
    fun onUiEvent(event: UiEventType)

    fun consumeUiEvents(
        scope: CoroutineScope,
        uiEventProducer: UiEventProducer<UiEventType>
    ) {
        scope.launch {
            uiEventProducer.uiEventFlow.collect { uiEvent ->
                if (BuildConfig.DEBUG) {
                    Timber.d(uiEvent.toString())
                }
                onUiEvent(uiEvent)
            }
        }
    }
}