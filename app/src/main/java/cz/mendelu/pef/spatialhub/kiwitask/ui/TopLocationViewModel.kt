package cz.mendelu.pef.spatialhub.kiwitask.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import cz.mendelu.pef.spatialhub.kiwitask.models.Search
import cz.mendelu.pef.spatialhub.kiwitask.repository.AppPreferencesRepository
import cz.mendelu.pef.spatialhub.kiwitask.repository.LocationRepository
import java.util.*

class TopLocationViewModel(
    app: Application,
    private val locationRepository: LocationRepository,
    private val dataStoreRepository: AppPreferencesRepository
) :
    AndroidViewModel(app) {
    val locationData: LiveData<Search> = liveData {
        val result = locationRepository.getLocations()
        if (result.isSuccessful) {
            dataStoreRepository.setLastSearchTime(Calendar.getInstance().timeInMillis)
            emit(result.body()!!)
        }
    }
}