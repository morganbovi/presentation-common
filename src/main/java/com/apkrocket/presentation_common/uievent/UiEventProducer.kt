package com.apkrocket.presentation_common.uievent

import kotlinx.coroutines.flow.Flow


interface UiEventProducer<UiEvent> {
    val uiEventFlow: Flow<UiEvent>
    fun postUiEvent(uiEvent: UiEvent)
}