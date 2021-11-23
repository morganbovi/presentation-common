package com.apkrocket.presentation_common

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle

@SuppressLint("Registered")
abstract class BaseActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}