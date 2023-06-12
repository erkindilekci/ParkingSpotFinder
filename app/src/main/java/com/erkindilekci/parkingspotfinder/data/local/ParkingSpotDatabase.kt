package com.erkindilekci.parkingspotfinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ParkingSpotEntity::class], version = 1, exportSchema = false)
abstract class ParkingSpotDatabase : RoomDatabase() {

    abstract val parkingSpotDao: ParkingSpotDao
}
