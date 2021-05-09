package cz.mendelu.pef.spatialhub.kiwitask.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import cz.mendelu.pef.spatialhub.kiwitask.repository.DefaultAppPreferencesRepository.PreferencesKeys.CURRENCY_KEY
import cz.mendelu.pef.spatialhub.kiwitask.repository.DefaultAppPreferencesRepository.PreferencesKeys.LAST_SEARCH_KEY
import cz.mendelu.pef.spatialhub.kiwitask.repository.DefaultAppPreferencesRepository.PreferencesKeys.SHOW_ON_BOARDING_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DefaultAppPreferencesRepository(private val dataStore: DataStore<Preferences>) :
    AppPreferencesRepository {

    private object PreferencesKeys {
        val LAST_SEARCH_KEY = longPreferencesKey("last_search")
        val CURRENCY_KEY = stringPreferencesKey("currency")
        val SHOW_ON_BOARDING_KEY = booleanPreferencesKey("show_on_boarding")
    }

    override val lastSearchTime: Flow<Long> = dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[LAST_SEARCH_KEY] ?: 0
        }

    override suspend fun setLastSearchTime(date: Long) {
        dataStore.edit { preferences ->
            preferences[LAST_SEARCH_KEY] = date
        }
    }

    override val currency: Flow<String>
        get() = dataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }.map { preferences ->
                preferences[CURRENCY_KEY] ?: ""
            }

    override suspend fun setCurrency(currency: String) {
        dataStore.edit { preferences ->
            preferences[CURRENCY_KEY] = currency
        }
    }

    override val showOnBoarding: Flow<Boolean>
        get() = dataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }.map { preferences ->
                preferences[SHOW_ON_BOARDING_KEY] ?: true
            }

    override suspend fun setShowOnBoarding(show: Boolean) {
        dataStore.edit { preferences ->
            preferences[SHOW_ON_BOARDING_KEY] = show
        }
    }
}