package co.uk.thewirelessguy.falconninelaunches.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.uk.thewirelessguy.falconninelaunches.data.ResponseHandler
import co.uk.thewirelessguy.falconninelaunches.di.DispatcherModule
import co.uk.thewirelessguy.falconninelaunches.model.State
import co.uk.thewirelessguy.falconninelaunches.model.app.LaunchSummary
import co.uk.thewirelessguy.falconninelaunches.repository.LaunchesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val repository: LaunchesRepository,
    private val savedStateHandle: SavedStateHandle,
    @DispatcherModule.IODispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _launchesListUIState = MutableStateFlow<State<List<LaunchSummary>>>(State.empty())
    val launchesListUIState = _launchesListUIState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    suspend fun fetchLaunchesList(refresh: Boolean = false) {
        Timber.d("fetchLaunchesList")
        if (refresh) _isRefreshing.value = true
        viewModelScope.launch(dispatcher) {
            try {
                _launchesListUIState.value = repository.getLaunchesList()
            } catch (ex: Exception) {
                Timber.e(ex)
                val responseHandler = ResponseHandler()
                _launchesListUIState.value = responseHandler.handleException(ex)
            }
            _isRefreshing.value = false
        }
    }
}