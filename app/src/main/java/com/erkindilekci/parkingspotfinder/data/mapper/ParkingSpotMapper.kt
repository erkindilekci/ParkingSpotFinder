package com.erkindilekci.parkingspotfinder.data.mapper

import com.erkindilekci.parkingspotfinder.data.local.ParkingSpotEntity
import com.erkindilekci.parkingspotfinder.domain.model.ParkingSpot

fun ParkingSpotEntity.toParkingSpot(): ParkingSpot {
    return ParkingSpot(id, lat, lng)
}

fun ParkingSpot.toParkingSpotEntity(): ParkingSpotEntity {
    return ParkingSpotEntity(id, lat, lng)
}
