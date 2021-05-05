package cz.mendelu.pef.spatialhub.kiwitask

import android.app.Application
import cz.mendelu.pef.spatialhub.kiwitask.di.networkModule
import cz.mendelu.pef.spatialhub.kiwitask.di.repositoryModule
import cz.mendelu.pef.spatialhub.kiwitask.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KiwiTaskApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KiwiTaskApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}