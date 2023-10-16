package org.thechance.service_taxi.api.dto.trip

import io.ktor.server.websocket.*
import kotlinx.coroutines.flow.MutableStateFlow

data class WebSocketTrip(
    val session: DefaultWebSocketServerSession,
    val isATaxiTrip: Boolean = false,
    val trip: MutableStateFlow<TripDto> = MutableStateFlow(TripDto())
)