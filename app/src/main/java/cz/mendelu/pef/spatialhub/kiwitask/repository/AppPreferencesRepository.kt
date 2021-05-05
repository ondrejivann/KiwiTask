package cz.mendelu.pef.spatialhub.kiwitask.repository

import kotlinx.coroutines.flow.Flow

interface AppPreferencesRepository {
    val lastSearchTime: Flow<Long>
    suspend fun setLastSearchTime(date: Long)
    val currency: Flow<String>
    suspend fun setCurrency(currency: String)
}