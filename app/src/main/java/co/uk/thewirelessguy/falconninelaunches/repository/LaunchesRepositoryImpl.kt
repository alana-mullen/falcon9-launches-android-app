package co.uk.thewirelessguy.falconninelaunches.repository

import co.uk.thewirelessguy.falconninelaunches.data.ApiInterface
import co.uk.thewirelessguy.falconninelaunches.data.ResponseHandler
import co.uk.thewirelessguy.falconninelaunches.di.DispatcherModule
import co.uk.thewirelessguy.falconninelaunches.model.State
import co.uk.thewirelessguy.falconninelaunches.model.app.LaunchSummary
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val client: ApiInterface,
    @DispatcherModule.IODispatcher private val dispatcher: CoroutineDispatcher,
) : LaunchesRepository {

    override suspend fun getLaunchesList(): State<List<LaunchSummary>> {
        val responseHandler = ResponseHandler()

        return withContext(dispatcher) {
            try {
                Timber.d("getLaunches")
                val response = client.getLaunches()
                val summary = LaunchSummary.from(response)
                return@withContext responseHandler.handleSuccess(summary)
            } catch (ex: Exception) {
                Timber.e(ex)
                return@withContext responseHandler.handleException(ex)
            }
        }
    }
}