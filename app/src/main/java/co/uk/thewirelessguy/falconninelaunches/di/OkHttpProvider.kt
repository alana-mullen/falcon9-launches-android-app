package co.uk.thewirelessguy.falconninelaunches.di

import co.uk.thewirelessguy.falconninelaunches.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import timber.log.Timber
import zerobranch.androidremotedebugger.logging.NetLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OkHttpProvider {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        // OkHttpClient for building http request url:
        val client = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            /**
             * Android Remote Debugger: Inspect network calls made by the app using your web browser.
             * @link https://github.com/zerobranch/android-remote-debugger
             */
            Timber.d("add interceptor")
            client.addNetworkInterceptor(NetLoggingInterceptor())
        }

        client.apply {
            addNetworkInterceptor(NetLoggingInterceptor())
        }
        return client.build()
    }
}