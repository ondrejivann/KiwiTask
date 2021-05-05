package cz.mendelu.pef.spatialhub.kiwitask.repository

import cz.mendelu.pef.spatialhub.kiwitask.api.TopLocationsAPI
import retrofit2.Response

class DefaultLocationRepository(private val locationsAPI: TopLocationsAPI) : LocationRepository {

    override suspend fun getLocations(): Response<String> = locationsAPI.getTopLocations()

}