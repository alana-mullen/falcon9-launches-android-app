package co.uk.thewirelessguy.falconninelaunches.extension

import timber.log.Timber
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(): String {
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date = dateFormat.parse(this)
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        formatter.format(date as Date)
    } catch (ex: Exception) {
        Timber.e(ex)
        ""
    }
}