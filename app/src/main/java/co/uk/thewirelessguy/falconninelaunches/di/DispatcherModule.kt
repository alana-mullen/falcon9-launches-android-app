package co.uk.thewirelessguy.falconninelaunches.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @Target(
        AnnotationTarget.PROPERTY,
        AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.TYPE
    )
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MainDispatcher

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DefaultDispatcher

    @Target(
        AnnotationTarget.PROPERTY,
        AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.TYPE
    )
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class IODispatcher

    @Target(
        AnnotationTarget.PROPERTY,
        AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.TYPE
    )
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UnconfinedDispatcher

    @Provides
    @MainDispatcher
    fun getMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Provides
    @DefaultDispatcher
    fun getDefaultDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    @IODispatcher
    fun getIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @UnconfinedDispatcher
    fun getUnconfinedDispatcher(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }
}