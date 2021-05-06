package cz.mendelu.pef.spatialhub.kiwitask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.mendelu.pef.spatialhub.kiwitask.models.Flight

@Database(
    entities = [Flight::class],
    version = 1
)

abstract class KiwiTaskDatabase : RoomDatabase() {
    abstract val flightsDao: FlightsDao
}