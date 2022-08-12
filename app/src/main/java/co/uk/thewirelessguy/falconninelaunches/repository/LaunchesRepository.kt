package co.uk.thewirelessguy.falconninelaunches.repository

import co.uk.thewirelessguy.falconninelaunches.model.State
import co.uk.thewirelessguy.falconninelaunches.model.app.LaunchSummary

interface LaunchesRepository {

    suspend fun getLaunchesList(): State<List<LaunchSummary>>
}