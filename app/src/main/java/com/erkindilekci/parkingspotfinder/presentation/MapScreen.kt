package com.erkindilekci.parkingspotfinder.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = false) }

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    properties = state.properties,
                    uiSettings = uiSettings,
                    onMapLongClick = { viewModel.onEvent(MapEvent.OnMapLongClicked(it)) }
                ) {
                    state.parkingSpots.forEach { parkingSpot ->
                        Marker(
                            position = LatLng(parkingSpot.lat, parkingSpot.lng),
                            title = "Parking Spot",
                            snippet = "Long click to delete",
                            onInfoWindowLongClick = {
                                viewModel.onEvent(MapEvent.OnInfoWindowLongClicked(parkingSpot))
                            },
                            onClick = {
                                it.showInfoWindow()
                                true
                            },
                            icon = BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_AZURE
                            )
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MapEvent.ToggleFalloutMap)
            }) {
                Icon(
                    imageVector = if (state.isFalloutMap) Icons.Default.ToggleOff else Icons.Default.ToggleOn,
                    contentDescription = null
                )
            }
        }
    )
}
