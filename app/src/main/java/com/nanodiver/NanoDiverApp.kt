package com.nanodiver

import android.app.Application
import android.os.Build
import android.util.Log

class NanoDiverApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i("NanoDiver", "Started on device ${Build.MODEL} API ${Build.VERSION.SDK_INT}")
    }
}
