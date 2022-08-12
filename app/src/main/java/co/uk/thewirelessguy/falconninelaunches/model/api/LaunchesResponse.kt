package co.uk.thewirelessguy.falconninelaunches.model.api

import kotlinx.serialization.*

@Serializable
data class LaunchesResponse (
    val id: String? = null,
    val name: String? = null,
    @SerialName("date_utc")
    val dateUTC: String? = null,
    val success: Boolean? = null,
    val links: Links? = null,
) {
    @Serializable
    data class Links (
        val patch: Patch? = null,
    )

    @Serializable
    data class Patch (
        val small: String? = null,
        val large: String? = null
    )
}

