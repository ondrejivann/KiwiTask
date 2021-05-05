package cz.mendelu.pef.spatialhub.kiwitask.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import cz.mendelu.pef.spatialhub.kiwitask.repository.LocationRepository

class TopLocationViewModel(app: Application, private val locationRepository: LocationRepository) :
    AndroidViewModel(app) {
    val locationData: LiveData<String> = liveData {
        val result = locationRepository.getLocations()
        if (result.isSuccessful) {
            emit(result.body()!!)
        }
    }
}