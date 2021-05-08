package cz.mendelu.pef.spatialhub.kiwitask.repository

import cz.mendelu.pef.spatialhub.kiwitask.models.Search
import retrofit2.Response

interface LocationRepository {
    suspend fun getLocations(dateFrom: String, dateTo: String): Response<Search>
}