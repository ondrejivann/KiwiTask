package cz.mendelu.pef.spatialhub.kiwitask.repository

import cz.mendelu.pef.spatialhub.kiwitask.api.TopLocationsAPI
import cz.mendelu.pef.spatialhub.kiwitask.models.Search
import retrofit2.Response

class DefaultLocationRepository(private val locationsAPI: TopLocationsAPI) : LocationRepository {

    override suspend fun getLocations(dateFrom: String, dateTo: String): Response<Search> = locationsAPI.getTopLocations(3, "popularity", 0, "en", "49.2-16.61-250km", "anywhere", dateFrom, dateTo, "oneway", 1, 1, 45, "skypicker-android")

}