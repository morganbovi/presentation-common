package com.apkrocket.presentation_common.navigation

interface NavRoute {
    val route: String
    val isBottomNavRoute: Boolean
        get() = false
}