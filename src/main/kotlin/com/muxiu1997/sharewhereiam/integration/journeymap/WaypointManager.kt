package com.muxiu1997.sharewhereiam.integration.journeymap

import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import journeymap.client.model.Waypoint
import net.minecraft.client.Minecraft

@SideOnly(Side.CLIENT)
object WaypointManager {
    var tempBeacon: Waypoint? = null
        private set

    private val transientBeaconCache = mutableMapOf<String, TransientBeacon>()

    fun hasActiveTempBeacon(): Boolean {
        return tempBeacon != null
    }

    fun clearActiveTempBeacon() {
        tempBeacon = null
    }

    fun toggleActiveTempBeacon(waypoint: Waypoint) {
        if (waypoint == this.tempBeacon) {
            this.tempBeacon = null
            return
        }
        this.tempBeacon = waypoint
    }

    fun addTransientBeacon(playerName: String, waypoint: Waypoint) {
        transientBeaconCache[playerName] = TransientBeacon(waypoint, Minecraft.getSystemTime())
    }

    fun getTransientBeacons(): List<Waypoint> {
        transientBeaconCache
            .filterValues { transientBeacon -> Minecraft.getSystemTime() - transientBeacon.start > 3000 }
            .forEach { (playerName, _) -> transientBeaconCache.remove(playerName) }
        return transientBeaconCache.values.map { it.waypoint }
    }

    data class TransientBeacon(val waypoint: Waypoint, val start: Long)
}
