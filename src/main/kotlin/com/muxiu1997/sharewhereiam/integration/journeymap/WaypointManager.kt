package com.muxiu1997.sharewhereiam.integration.journeymap

import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import journeymap.client.model.Waypoint

@SideOnly(Side.CLIENT)
object WaypointManager {
    var waypoint: Waypoint? = null
        private set

    fun hasActiveWaypoint(): Boolean {
        return waypoint != null
    }

    fun clearActiveWaypoint() {
        waypoint = null
    }

    fun toggleActiveWaypoint(waypoint: Waypoint) {
        if (waypoint == this.waypoint) {
            this.waypoint = null
            return
        }
        this.waypoint = waypoint
    }
}
