package co.uk.thewirelessguy.falconninelaunches.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import co.uk.thewirelessguy.falconninelaunches.model.app.LaunchSummary

class LaunchItemProvider : PreviewParameterProvider<LaunchSummary> {
    override val values: Sequence<LaunchSummary> = sequenceOf(
        LaunchSummary(
            id = "1",
            title = "Some Rocket",
            launchDate = "1-1-2022",
            missionSuccess = true,
            image = ""
        ),
        LaunchSummary(
            id = "2",
            title = "Some Other Rocket",
            launchDate = "2-2-2022",
            missionSuccess = false,
            image = ""
        )
    )
}