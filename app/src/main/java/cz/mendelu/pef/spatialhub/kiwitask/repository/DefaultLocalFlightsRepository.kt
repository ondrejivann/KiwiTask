package cz.mendelu.pef.spatialhub.kiwitask.repository

import cz.mendelu.pef.spatialhub.kiwitask.database.FlightsDao
import cz.mendelu.pef.spatialhub.kiwitask.models.Flight
import kotlinx.coroutines.flow.Flow

class DefaultLocalFlightsRepository(private val flightsDao: FlightsDao) : LocalFlightsRepository {

    override fun getAllFlights(): Flow<List<Flight>> = flightsDao.getAllFlights()

    override suspend fun insertFlights(flights: List<Flight>) {
        flightsDao.clearAndInsertFlights(flights)
    }
}