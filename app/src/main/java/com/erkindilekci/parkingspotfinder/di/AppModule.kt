package com.erkindilekci.parkingspotfinder.di

import android.content.Context
import androidx.room.Room
import com.erkindilekci.parkingspotfinder.data.local.ParkingSpotDatabase
import com.erkindilekci.parkingspotfinder.data.repository.ParkingSpotRepositoryImpl
import com.erkindilekci.parkingspotfinder.domain.repository.ParkingSpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideParkingSpotDatabase(
        @ApplicationContext context: Context
    ): ParkingSpotDatabase = Room.databaseBuilder(
        context,
        ParkingSpotDatabase::class.java,
        "parking_database"
    ).build()

    @Provides
    @Singleton
    fun provideParkingSpotRepository(
        db: ParkingSpotDatabase
    ): ParkingSpotRepository = ParkingSpotRepositoryImpl(db.parkingSpotDao)
}
