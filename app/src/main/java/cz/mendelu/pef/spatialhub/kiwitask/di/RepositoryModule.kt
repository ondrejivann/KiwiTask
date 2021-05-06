package cz.mendelu.pef.spatialhub.kiwitask.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import cz.mendelu.pef.spatialhub.kiwitask.api.TopLocationsAPI
import cz.mendelu.pef.spatialhub.kiwitask.database.FlightsDao
import cz.mendelu.pef.spatialhub.kiwitask.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { provideLocationRepository(get()) }
    single { provideDataStoreRepository(androidContext().dataStore) }
    single { provideLocalFlightsRepository(get()) }
}

fun provideLocationRepository(locationAPI: TopLocationsAPI): LocationRepository {
    return DefaultLocationRepository(locationAPI)
}

fun provideDataStoreRepository(dataStore: DataStore<Preferences>): AppPreferencesRepository {
    return DefaultAppPreferencesRepository(dataStore)
}

fun provideLocalFlightsRepository(flightsDao: FlightsDao): LocalFlightsRepository {
    return DefaultLocalFlightsRepository(flightsDao)
}

private const val APP_DATA_STORE_NAME = "app_data_store"

private val Context.dataStore by preferencesDataStore(name = APP_DATA_STORE_NAME)