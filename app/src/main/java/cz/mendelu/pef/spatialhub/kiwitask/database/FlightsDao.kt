package cz.mendelu.pef.spatialhub.kiwitask.database

import androidx.room.*
import cz.mendelu.pef.spatialhub.kiwitask.models.Flight
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightsDao {

    @Query("SELECT * FROM Flights")
    fun getAllFlights(): Flow<List<Flight>>

    @Query("SELECT * FROM Flights")
    suspend fun getAllFlightsOnce(): List<Flight>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlights(flights: List<Flight>)

    @Query("DELETE FROM Flights")
    suspend fun deleteAllFlights()

    @Transaction
    suspend fun clearAndInsertFlights(flights: List<Flight>) {
        deleteAllFlights()
        insertFlights(flights)
    }
}