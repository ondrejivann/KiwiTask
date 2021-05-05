package cz.mendelu.pef.spatialhub.kiwitask.api

import cz.mendelu.pef.spatialhub.kiwitask.models.Search
import retrofit2.Response
import retrofit2.http.GET

interface TopLocationsAPI {
    @GET("flights?v=3&sort=popularity&asc=0&locale=en&daysInDestinationFrom=&daysInDestinationTo=&affilid=&children=0&infants=0&flyFrom=49.2-16.61-250km&to=anywhere&featureName=aggregateResults&dateFrom=06/06/2021&dateTo=06/06/2021&typeFlight=oneway&returnFrom=&returnTo=&one_per_date=0&oneforcity=1&wait_for_refresh=0&adults=1&limit=45&partner=skypicker-android")
    suspend fun getTopLocations(): Response<Search>
}