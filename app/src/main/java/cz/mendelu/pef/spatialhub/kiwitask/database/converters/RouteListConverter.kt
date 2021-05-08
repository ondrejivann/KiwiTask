package cz.mendelu.pef.spatialhub.kiwitask.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cz.mendelu.pef.spatialhub.kiwitask.models.Route
import java.lang.reflect.Type

class RouteListConverter {

    @TypeConverter
    fun fromRouteList(routeList: List<Route?>?): String? {
        if (routeList == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Route?>?>() {}.type
        return gson.toJson(routeList, type)
    }

    @TypeConverter
    fun toRouteList(routeString: String?): List<Route>? {
        if (routeString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Route?>?>() {}.type
        return gson.fromJson<List<Route>>(routeString, type)
    }
}