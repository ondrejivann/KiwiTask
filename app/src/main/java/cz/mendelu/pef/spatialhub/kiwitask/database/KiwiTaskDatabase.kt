package cz.mendelu.pef.spatialhub.kiwitask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.mendelu.pef.spatialhub.kiwitask.database.converters.AvailabilityConverter
import cz.mendelu.pef.spatialhub.kiwitask.database.converters.CountryConverter
import cz.mendelu.pef.spatialhub.kiwitask.database.converters.RouteListConverter
import cz.mendelu.pef.spatialhub.kiwitask.database.converters.StringListConverter
import cz.mendelu.pef.spatialhub.kiwitask.models.Flight

@Database(
    entities = [Flight::class],
    version = 1
)

// Pro zjednoduseni prevadim do db na stringy, jinak udelat normal db.
@TypeConverters(
    StringListConverter::class,
    AvailabilityConverter::class,
    RouteListConverter::class,
    CountryConverter::class
)
abstract class KiwiTaskDatabase : RoomDatabase() {
    abstract val flightsDao: FlightsDao
}