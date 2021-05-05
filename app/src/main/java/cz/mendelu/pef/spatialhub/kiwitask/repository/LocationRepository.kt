package cz.mendelu.pef.spatialhub.kiwitask.repository

import retrofit2.Response

interface LocationRepository {
    suspend fun getLocations(): Response<String>
}