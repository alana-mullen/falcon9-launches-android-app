package co.uk.thewirelessguy.falconninelaunches

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import zerobranch.androidremotedebugger.AndroidRemoteDebugger

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initLogging(this, applicationContext)
    }

    private fun initLogging(context: Context, applicationContext: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            AndroidRemoteDebugger.init(applicationContext)
        }
    }
}