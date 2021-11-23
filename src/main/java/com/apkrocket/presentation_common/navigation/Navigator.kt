package com.apkrocket.presentation_common.navigation

import kotlinx.coroutines.flow.Flow

interface Navigator {

    val navFlow: Flow<String>

    suspend fun navigate(route: String)

}