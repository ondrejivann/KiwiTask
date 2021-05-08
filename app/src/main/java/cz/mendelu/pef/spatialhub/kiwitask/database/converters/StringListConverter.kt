package cz.mendelu.pef.spatialhub.kiwitask.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken

class StringListConverter {
    @TypeConverter
    fun fromList(list: MutableList<String>?): String? {
        if (list == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<MutableList<String>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toList(images: String?): MutableList<String>? {
        if (images == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson<MutableList<String>>(images, type)
    }
}