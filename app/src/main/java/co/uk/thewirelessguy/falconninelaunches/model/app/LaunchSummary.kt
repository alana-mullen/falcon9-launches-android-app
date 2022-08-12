package co.uk.thewirelessguy.falconninelaunches.model.app

import co.uk.thewirelessguy.falconninelaunches.extension.formatDate
import co.uk.thewirelessguy.falconninelaunches.model.api.LaunchesResponse

data class LaunchSummary(
    val id: String,
    val title: String,
    val launchDate: String,
    val missionSuccess: Boolean?,
    val image: String
) {
    companion object {
        fun from(launch: LaunchesResponse): LaunchSummary? {
            if (launch.id.isNullOrEmpty() || launch.name.isNullOrEmpty()) return null
            val id = launch.id
            val launchTitle = launch.name
            val date = launch.dateUTC?.formatDate().orEmpty()
            val success = launch.success
            val thumbnail = launch.links?.patch?.small.orEmpty()
            return LaunchSummary(
                id = id,
                title = launchTitle,
                launchDate = date,
                missionSuccess = success,
                image = thumbnail
            )
        }

        fun from(launches: List<LaunchesResponse>): List<LaunchSummary> {
            return launches.mapNotNull { launch ->
                from(launch)
            }
        }
    }
}