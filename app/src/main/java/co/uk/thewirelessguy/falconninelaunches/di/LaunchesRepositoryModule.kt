package co.uk.thewirelessguy.falconninelaunches.di

import co.uk.thewirelessguy.falconninelaunches.data.ApiInterface
import co.uk.thewirelessguy.falconninelaunches.repository.LaunchesRepository
import co.uk.thewirelessguy.falconninelaunches.repository.LaunchesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LaunchesRepositoryModule {

    @Singleton
    @Provides
    fun provideLaunchesRepository(
        apiInterface: ApiInterface,
        @DispatcherModule.IODispatcher dispatcher: CoroutineDispatcher
    ) : LaunchesRepository {
        return LaunchesRepositoryImpl(
            client = apiInterface,
            dispatcher = dispatcher
        )
    }
}