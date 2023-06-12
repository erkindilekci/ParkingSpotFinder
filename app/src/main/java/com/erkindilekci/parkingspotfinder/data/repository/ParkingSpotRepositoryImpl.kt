package com.erkindilekci.parkingspotfinder.data.repository

import com.erkindilekci.parkingspotfinder.data.local.ParkingSpotDao
import com.erkindilekci.parkingspotfinder.data.mapper.toParkingSpot
import com.erkindilekci.parkingspotfinder.data.mapper.toParkingSpotEntity
import com.erkindilekci.parkingspotfinder.domain.model.ParkingSpot
import com.erkindilekci.parkingspotfinder.domain.repository.ParkingSpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ParkingSpotRepositoryImpl(
    private val dao: ParkingSpotDao
) : ParkingSpotRepository {

    override suspend fun insertParkingSpot(spot: ParkingSpot) {
        dao.insertParkingSpot(spot.toParkingSpotEntity())
    }

    override suspend fun deleteParkingSpot(spot: ParkingSpot) {
        dao.deleteParkingSpot(spot.toParkingSpotEntity())
    }

    override fun getParkingSpots(): Flow<List<ParkingSpot>> {
        return dao.getParkingSpots().map { parkingSpots ->
            parkingSpots.map { it.toParkingSpot() }
        }
    }
}
