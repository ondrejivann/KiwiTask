package cz.mendelu.pef.spatialhub.kiwitask.di

import android.app.Application
import androidx.room.Room
import cz.mendelu.pef.spatialhub.kiwitask.database.FlightsDao
import cz.mendelu.pef.spatialhub.kiwitask.database.KiwiTaskDatabase
import cz.mendelu.pef.spatialhub.kiwitask.others.Constants
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideFlightsDao(get()) }
}

fun provideDatabase(application: Application): KiwiTaskDatabase {
    return Room.databaseBuilder(application, KiwiTaskDatabase::class.java, Constants.DATABASE_NAME)
        .fallbackToDestructiveMigration().build()
}

fun provideFlightsDao(database: KiwiTaskDatabase): FlightsDao {
    return database.flightsDao
}