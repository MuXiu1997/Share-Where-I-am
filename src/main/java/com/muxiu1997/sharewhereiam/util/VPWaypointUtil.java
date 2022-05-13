package com.muxiu1997.sharewhereiam.util;

import com.sinthoras.visualprospecting.integration.journeymap.JourneyMapState;
import com.sinthoras.visualprospecting.integration.journeymap.drawsteps.ClickableDrawStep;
import com.sinthoras.visualprospecting.integration.journeymap.render.LayerRenderer;
import com.sinthoras.visualprospecting.integration.journeymap.render.WaypointProviderLayerRenderer;
import com.sinthoras.visualprospecting.integration.model.waypoints.Waypoint;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.ReflectionHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.lang.reflect.Field;

public class VPWaypointUtil {
    @Nullable
    @Optional.Method(modid = "visualprospecting")
    public static journeymap.client.model.Waypoint getHoveredWaypoint() {
        for (LayerRenderer layer : JourneyMapState.instance.renderers) {
            if (layer instanceof WaypointProviderLayerRenderer) {
                final Field FieldHoveredDrawStep = ReflectionHelper.findField(WaypointProviderLayerRenderer.class, "hoveredDrawStep");
                @Nullable ClickableDrawStep hoveredDrawStep = null;
                try {
                    hoveredDrawStep = (ClickableDrawStep) FieldHoveredDrawStep.get(layer);
                } catch (Exception ignored) {
                }
                if (hoveredDrawStep != null) {
                    final Waypoint waypoint = hoveredDrawStep.getLocationProvider().toWaypoint();
                    if (waypoint != null) {
                        return getJMWaypoint(waypoint);
                    }
                }
            }
        }
        return null;
    }

    @Nonnull
    @Optional.Method(modid = "visualprospecting")
    public static journeymap.client.model.Waypoint getJMWaypoint(@Nonnull Waypoint waypoint) {
        return new journeymap.client.model.Waypoint(
            waypoint.label,
            waypoint.blockX, waypoint.blockY, waypoint.blockZ,
            new Color(waypoint.color),
            journeymap.client.model.Waypoint.Type.Normal,
            waypoint.dimensionId
        );
    }
}
