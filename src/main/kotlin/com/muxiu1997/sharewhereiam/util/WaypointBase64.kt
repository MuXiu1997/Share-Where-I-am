package com.muxiu1997.sharewhereiam.util

import journeymap.client.model.Waypoint
import java.nio.charset.StandardCharsets
import java.util.*

object WaypointBase64 {
    fun toBase64(waypoint: Waypoint): String {
        val waypointJSON = waypoint.toString()
        return Base64.getEncoder().encodeToString(waypointJSON.toByteArray(StandardCharsets.UTF_8))
    }

    fun fromBase64(base64: String?): Waypoint {
        val waypointJSON = String(Base64.getDecoder().decode(base64), StandardCharsets.UTF_8)
        return Waypoint.fromString(waypointJSON)
    }
}
