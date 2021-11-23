package com.apkrocket.presentation_common.uievent

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import com.apkrocket.domain.SingleLiveEvent


class BaseUiEventProducer<U : UiEvent> : UiEventProducer<U> {

    override val uiEventSingleLiveEvent: SingleLiveEvent<U> = SingleLiveEvent()

    @SuppressLint("RestrictedApi")
    override fun postUiEvent(uiEvent: U) {
        ArchTaskExecutor.getMainThreadExecutor().execute {
            uiEventSingleLiveEvent.value = uiEvent
        }
    }

}