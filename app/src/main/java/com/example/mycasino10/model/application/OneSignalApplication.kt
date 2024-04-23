package com.example.mycasino10.model.application

import android.app.Application
import com.onesignal.OneSignal

class OneSignalApplication:Application() {

    private val ONESIGNAL_APP_ID = "2d62e47a-df18-4fcf-a52d-b12ac5e3a93a"

    override fun onCreate() {
        super.onCreate()

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

    }

}