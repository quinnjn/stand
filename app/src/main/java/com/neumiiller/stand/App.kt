package com.neumiiller.stand

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

/**
 * Created by qneumiiller on 1/22/16.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        connectCrashlytics()
    }

    private fun connectCrashlytics() {
        Fabric.with(this, Crashlytics())
    }

}