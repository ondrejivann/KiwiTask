package cz.mendelu.pef.spatialhub.kiwitask.others

import java.text.NumberFormat
import java.util.*

object NumberUtils {
    fun getCurrencyString(amount: Int): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("EUR")
        return format.format(amount)
    }
}