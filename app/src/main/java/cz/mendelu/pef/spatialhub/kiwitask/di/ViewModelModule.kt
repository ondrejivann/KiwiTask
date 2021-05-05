package cz.mendelu.pef.spatialhub.kiwitask.di

import cz.mendelu.pef.spatialhub.kiwitask.ui.TopLocationViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TopLocationViewModel(androidApplication(), get())
    }
}