package co.uk.thewirelessguy.falconninelaunches.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@ExperimentalSerializationApi
fun <T : Any> getApiClient(
    clazz: Class<T>,
    okHttpClient: OkHttpClient
): T {

    val contentType = "application/json".toMediaType()
    // Use kotlinx.serialization
    // https://github.com/Kotlin/kotlinx.serialization
    val json = Json {
        // Enable writing deserialization models that don't include fields we're not using:
        ignoreUnknownKeys = true
    }

    return Retrofit.Builder()
        .baseUrl("https://api.spacexdata.com/v5/")
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(okHttpClient)
        .build().create(clazz)
}