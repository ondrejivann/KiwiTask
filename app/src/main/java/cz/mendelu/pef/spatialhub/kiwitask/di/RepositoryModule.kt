package cz.mendelu.pef.spatialhub.kiwitask.di

import cz.mendelu.pef.spatialhub.kiwitask.api.TopLocationsAPI
import cz.mendelu.pef.spatialhub.kiwitask.repository.DefaultLocationRepository
import cz.mendelu.pef.spatialhub.kiwitask.repository.LocationRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { provideLocationRepository(get()) }
}

fun provideLocationRepository(locationAPI: TopLocationsAPI): LocationRepository {
    return DefaultLocationRepository(locationAPI)
}