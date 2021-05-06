package cz.mendelu.pef.spatialhub.kiwitask.repository

import cz.mendelu.pef.spatialhub.kiwitask.models.Flight
import kotlinx.coroutines.flow.Flow

interface LocalFlightsRepository {
    fun getAllFlights() : Flow<List<Flight>>
    suspend fun insertFlights(flights: List<Flight>)
}