package com.erkindilekci.parkingspotfinder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ParkingSpotEntity(
    @PrimaryKey
    val id: Int? = null,
    val lat: Double,
    val lng: Double
)
