package cz.mendelu.pef.spatialhub.kiwitask.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.spatialhub.kiwitask.models.Flight
import cz.mendelu.pef.spatialhub.kiwitask.models.Result
import cz.mendelu.pef.spatialhub.kiwitask.others.DateTimeUtils
import cz.mendelu.pef.spatialhub.kiwitask.repository.AppPreferencesRepository
import cz.mendelu.pef.spatialhub.kiwitask.repository.LocalFlightsRepository
import cz.mendelu.pef.spatialhub.kiwitask.repository.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class TopLocationViewModel(
    app: Application,
    private val locationRepository: LocationRepository,
    private val dataStoreRepository: AppPreferencesRepository,
    private val localFlightsRepository: LocalFlightsRepository
) : AndroidViewModel(app) {

    private val _flights = MutableStateFlow<Result<List<Flight>>>(Result.Loading)
    val flights: StateFlow<Result<List<Flight>>> = _flights

    init {
        fetchFlights()
    }

    private fun fetchFlights() = viewModelScope.launch {
        dataStoreRepository.lastSearchTime.collect { lastSearch ->
            if (DateTimeUtils.isYesterdayOrOlder(lastSearch)) {
                val result = locationRepository.getLocations()
                if (result.isSuccessful) {
                    result.body()?.let { search ->
                        dataStoreRepository.setLastSearchTime(Calendar.getInstance().timeInMillis)
                        search.currency?.let { currency ->
                            dataStoreRepository.setCurrency(currency)
                        }
                        val currentFlights = localFlightsRepository.getAllFlightsOnce()
                        if (!currentFlights.isNullOrEmpty()) {
                            //val flightList = search.flights.slice(0..4)
                            //Log.d("TopLocationsLog", flightList.toString())
                            localFlightsRepository.insertFlights(search.flights.slice(0..4))
                        } else {
                            localFlightsRepository.insertFlights(
                                getUniqueFlights( currentFlights, search.flights)
                            )
                        }
                    }
                } else {
                    _flights.value = Result.Error(result.message())
                }
            } else {
                localFlightsRepository.getAllFlights().collect { flightsList ->
                    if (flightsList.isNotEmpty()) {
                        _flights.value = Result.Success(flightsList)
                    } else {
                        _flights.value = Result.Empty
                    }
                }
            }
        }
    }

    private fun getUniqueFlights(
        currentFlights: List<Flight>,
        newFlights: List<Flight>
    ): List<Flight> {
        val newUniqueFlights: MutableList<Flight> = mutableListOf()
        var index = 0
        while (newUniqueFlights.size < 5 && index < newFlights.size) {
            var addNewFlight = true
            currentFlights.forEach { currentFlight ->
                if (newFlights[index].apiId == currentFlight.apiId || newFlights[index].destinationCity == currentFlight.destinationCity) {
                    addNewFlight = false
                }
            }
            if (addNewFlight) {
                newUniqueFlights.add(newFlights[index])
            }
            index++
        }
        return newUniqueFlights
    }
}