package cz.mendelu.pef.spatialhub.kiwitask.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.spatialhub.kiwitask.repository.AppPreferencesRepository
import kotlinx.coroutines.launch

class OnBoardingViewModel(app: Application, private val dataStoreRepository: AppPreferencesRepository): AndroidViewModel(app) {

    fun showOnBoarding(show: Boolean) {
        viewModelScope.launch {
            dataStoreRepository.setShowOnBoarding(show)
        }
    }

    fun showOnBoarding() = dataStoreRepository.showOnBoarding.asLiveData()
}