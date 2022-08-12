package co.uk.thewirelessguy.falconninelaunches.data

import co.uk.thewirelessguy.falconninelaunches.model.api.LaunchesResponse
import retrofit2.http.GET

interface ApiInterface {

    @GET("launches")
    suspend fun getLaunches(): List<LaunchesResponse>
}