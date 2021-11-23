package com.apkrocket.presentation_common.uievent

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.apkrocket.presentation_common.BuildConfig
import timber.log.Timber

interface UiEventConsumer<UiEventType : UiEvent> {
    fun onUiEvent(event: UiEventType)

    fun consumeUiEvents(
        lifecycleOwner: LifecycleOwner,
        uiEventProducer: UiEventProducer<UiEventType>
    ) {
        uiEventProducer.uiEventSingleLiveEvent.observe(lifecycleOwner, Observer {
//            logStateBreadcrumb(it)
            if (BuildConfig.DEBUG) {
                Timber.d(it.toString())
            }
            this.onUiEvent(it)
        })
    }
}