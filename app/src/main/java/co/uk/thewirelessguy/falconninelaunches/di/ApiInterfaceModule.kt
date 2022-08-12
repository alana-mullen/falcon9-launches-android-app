package co.uk.thewirelessguy.falconninelaunches.di

import co.uk.thewirelessguy.falconninelaunches.data.ApiInterface
import co.uk.thewirelessguy.falconninelaunches.data.getApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiInterfaceModule {

    @Provides
    @Singleton
    fun providesApiInterface(
        okHttpClient: OkHttpClient
    ): ApiInterface = getApiClient(
        clazz = ApiInterface::class.java,
        okHttpClient = okHttpClient
    )
}