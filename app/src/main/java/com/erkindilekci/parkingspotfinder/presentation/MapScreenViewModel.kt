package com.erkindilekci.parkingspotfinder.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.parkingspotfinder.domain.model.ParkingSpot
import com.erkindilekci.parkingspotfinder.domain.repository.ParkingSpotRepository
import com.google.android.gms.maps.model.MapStyleOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val repository: ParkingSpotRepository
) : ViewModel() {

    var state by mutableStateOf(MapState())

    init {
        viewModelScope.launch {
            repository.getParkingSpots().collectLatest { parkingSpots ->
                state = state.copy(parkingSpots = parkingSpots)
            }
        }
    }

    fun onEvent(event: MapEvent) {
        when (event) {
            MapEvent.ToggleFalloutMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if (state.isFalloutMap) {
                            null
                        } else MapStyleOptions(MapStyle.json)
                    ),
                    isFalloutMap = !state.isFalloutMap
                )
            }

            is MapEvent.OnInfoWindowLongClicked -> {
                viewModelScope.launch {
                    repository.deleteParkingSpot(event.spot)
                }
            }

            is MapEvent.OnMapLongClicked -> {
                viewModelScope.launch {
                    repository.insertParkingSpot(
                        ParkingSpot(
                            lat = event.latLng.latitude,
                            lng = event.latLng.longitude
                        )
                    )
                }
            }
        }
    }
}
