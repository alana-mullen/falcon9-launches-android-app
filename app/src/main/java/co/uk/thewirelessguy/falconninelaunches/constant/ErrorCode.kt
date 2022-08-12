package co.uk.thewirelessguy.falconninelaunches.constant

import androidx.annotation.StringRes
import co.uk.thewirelessguy.falconninelaunches.R

interface ErrorCode {
    val resourceId: Int
}

enum class HttpErrorCode(@StringRes override val resourceId: Int): ErrorCode {

    SOCKET_TIME_OUT(R.string.error_socket_timeout),
    INVALID_REQUEST(R.string.error_http_400),
    NO_INTERNET(R.string.error_no_internet),
    UNAUTHORISED(R.string.error_http_401),
    NOT_FOUND(R.string.error_http_404),
    UNKNOWN(R.string.error_general_unknown);

    companion object {
        fun fromCode(statusCode: Int): HttpErrorCode {
            return when(statusCode) {
                400 -> INVALID_REQUEST
                401 -> UNAUTHORISED
                404 -> NOT_FOUND
                else -> UNKNOWN
            }
        }
    }
}

enum class GenericErrorCode(@StringRes override val resourceId: Int): ErrorCode {
    UNKNOWN_ERROR(R.string.error_general_unknown),
    READ_ERROR(R.string.error_general_read_error),
}
