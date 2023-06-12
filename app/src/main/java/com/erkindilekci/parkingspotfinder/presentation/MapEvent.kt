package com.erkindilekci.parkingspotfinder.presentation

import com.erkindilekci.parkingspotfinder.domain.model.ParkingSpot
import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    object ToggleFalloutMap : MapEvent()
    data class OnMapLongClicked(val latLng: LatLng) : MapEvent()
    data class OnInfoWindowLongClicked(val spot: ParkingSpot) : MapEvent()
}
