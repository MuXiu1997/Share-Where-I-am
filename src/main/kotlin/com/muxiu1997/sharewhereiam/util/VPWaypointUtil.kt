package com.muxiu1997.sharewhereiam.util

import com.muxiu1997.sharewhereiam.integration.VISUAL_PROSPECTING_MODID
import com.sinthoras.visualprospecting.integration.journeymap.JourneyMapState
import com.sinthoras.visualprospecting.integration.journeymap.drawsteps.ClickableDrawStep
import com.sinthoras.visualprospecting.integration.journeymap.render.WaypointProviderLayerRenderer
import cpw.mods.fml.common.Optional
import cpw.mods.fml.relauncher.ReflectionHelper
import journeymap.client.model.Waypoint
import java.awt.Color

object VPWaypointUtil {
    @JvmStatic
    @Optional.Method(modid = VISUAL_PROSPECTING_MODID)
    fun getHoveredWaypoint(): Waypoint? {
        val fieldHoveredDrawStep = ReflectionHelper.findField(
            WaypointProviderLayerRenderer::class.java,
            "hoveredDrawStep",
        )
        for (layer in JourneyMapState.instance.renderers) {
            if (layer !is WaypointProviderLayerRenderer) continue
            val hoveredDrawStep = try {
                fieldHoveredDrawStep[layer] as ClickableDrawStep
            } catch (ignored: Exception) {
                continue
            }
            val waypoint = hoveredDrawStep.locationProvider?.toWaypoint() ?: continue
            return waypoint.getJMWaypoint()
        }
        return null
    }

    @Optional.Method(modid = VISUAL_PROSPECTING_MODID)
    private fun com.sinthoras.visualprospecting.integration.model.waypoints.Waypoint.getJMWaypoint(): Waypoint {
        return Waypoint(
            this.label,
            this.blockX, this.blockY, this.blockZ,
            Color(this.color),
            Waypoint.Type.Normal,
            this.dimensionId
        )
    }
}
