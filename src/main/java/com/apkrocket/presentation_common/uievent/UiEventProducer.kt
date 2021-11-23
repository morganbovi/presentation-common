package com.apkrocket.presentation_common.uievent

import com.apkrocket.domain.SingleLiveEvent


interface UiEventProducer<UiEvent> {
    val uiEventSingleLiveEvent: SingleLiveEvent<UiEvent>
    fun postUiEvent(uiEvent: UiEvent)
}