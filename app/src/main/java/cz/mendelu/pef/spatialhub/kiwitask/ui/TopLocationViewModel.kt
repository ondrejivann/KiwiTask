package cz.mendelu.pef.spatialhub.kiwitask.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import cz.mendelu.pef.spatialhub.kiwitask.models.Search
import cz.mendelu.pef.spatialhub.kiwitask.repository.LocationRepository

class TopLocationViewModel(app: Application, private val locationRepository: LocationRepository) :
    AndroidViewModel(app) {
    val locationData: LiveData<Search> = liveData {
        val result = locationRepository.getLocations()
        if (result.isSuccessful) {
            Log.d("VieqModelLog", result.body()!!.toString())
            emit(result.body()!!)
        }
    }
}