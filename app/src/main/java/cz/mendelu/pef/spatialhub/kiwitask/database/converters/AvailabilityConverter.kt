package cz.mendelu.pef.spatialhub.kiwitask.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import cz.mendelu.pef.spatialhub.kiwitask.models.Availability

class AvailabilityConverter {
    @TypeConverter
    fun fromAvailability(value: Availability?): String? = Gson().toJson(value)

    @TypeConverter
    fun stringToAvailability(string: String): Availability? =
        Gson().fromJson(string, Availability::class.java)
}