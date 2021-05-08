package cz.mendelu.pef.spatialhub.kiwitask.others

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    private const val DATE_FORMAT_EN = "yyyy-MM-dd"
    private const val DATE_FORMAT_CS = "dd. MM. yyyy"
    private const val HOURS_MINUTES_FORMAT = "HH:mm"

    fun isYesterdayOrOlder(time: Long): Boolean = time < getLastMidnight()

    private fun getLastMidnight(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        return calendar.timeInMillis
    }

    fun getDateFromUnix(timestamp: Long): String {
        val date = Date(timestamp*1000L)
        val localeLanguage = Locale.getDefault().language
        val sdf = if (localeLanguage == "cs" || localeLanguage == "sk")
            SimpleDateFormat(DATE_FORMAT_CS, Locale.getDefault())
        else
            SimpleDateFormat(DATE_FORMAT_EN, Locale.getDefault())
        return sdf.format(date)
    }

    fun getTimeFromUnix(timestamp: Long): String {
        val date = Date(timestamp*1000L)
        val sdf = SimpleDateFormat(HOURS_MINUTES_FORMAT, Locale.getDefault())
        return sdf.format(date)
    }
}