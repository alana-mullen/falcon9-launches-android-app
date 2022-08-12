package co.uk.thewirelessguy.falconninelaunches.data

import co.uk.thewirelessguy.falconninelaunches.constant.HttpErrorCode
import co.uk.thewirelessguy.falconninelaunches.model.State
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): State<T> {
        return State.success(data)
    }

    fun <T : Any> handleException(exception: Throwable): State<T> {
        Timber.d("handleException: $exception")
        return when (exception) {
            is HttpException -> State.error(HttpErrorCode.fromCode(exception.code()))
            is SocketTimeoutException -> State.error(HttpErrorCode.SOCKET_TIME_OUT)
            is IOException -> State.error(HttpErrorCode.NO_INTERNET)
            else -> {
                Timber.d("Error: %s", exception.message)
                State.error(HttpErrorCode.UNKNOWN)
            }
        }
    }
}