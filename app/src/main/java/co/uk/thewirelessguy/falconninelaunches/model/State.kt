package co.uk.thewirelessguy.falconninelaunches.model

import co.uk.thewirelessguy.falconninelaunches.constant.ErrorCode
import kotlinx.serialization.json.JsonObject

/**
 * State Management for UI & Data.
 */
sealed class State<T> {
    class Loading<T>() : State<T>()

    class Empty<T> : State<T>()

    data class Success<T>(val data: T) : State<T>()

    data class Error<T>(val errorCode: ErrorCode?, val errorContext: JsonObject?) : State<T>()

    companion object {

        /**
         * Returns [State.Loading] instance.
         */
        fun <T> loading() = Loading<T>()

        /**
         * Returns [State.Empty] instance.
         */
        fun <T> empty() = Empty<T>()

        /**
         * Returns [State.Success] instance.
         * @param data Data to emit with status.
         */
        fun <T> success(data: T) =
            Success(data)

        /**
         * returns [State.Error] instance
         * @param errorCode An error code with associated resource string
         * @param errorContext Any context to assist with rendering and handling the error
         */
        fun <T> error(errorCode: ErrorCode, errorContext: JsonObject? = null) =
            Error<T>(errorCode, errorContext)
    }
}