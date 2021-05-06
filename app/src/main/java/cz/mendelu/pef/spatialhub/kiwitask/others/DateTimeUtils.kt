package cz.mendelu.pef.spatialhub.kiwitask.others

import java.util.*

object DateTimeUtils {

    fun isYesterdayOrOlder(time: Long): Boolean = time < getLastMidnight()

    private fun getLastMidnight(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        return calendar.timeInMillis
    }
}