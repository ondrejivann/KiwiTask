package cz.mendelu.pef.spatialhub.kiwitask.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import cz.mendelu.pef.spatialhub.kiwitask.models.CountryDestination

class CountryConverter {
    @TypeConverter
    fun fromAvailability(value: CountryDestination?): String? = Gson().toJson(value)

    @TypeConverter
    fun stringToAvailability(string: String): CountryDestination? =
        Gson().fromJson(string, CountryDestination::class.java)
}