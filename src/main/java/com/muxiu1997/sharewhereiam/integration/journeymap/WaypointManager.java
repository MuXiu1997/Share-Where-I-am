package com.muxiu1997.sharewhereiam.integration.journeymap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import journeymap.client.model.Waypoint;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class WaypointManager {

    public static WaypointManager INSTANCE = new WaypointManager();

    @Nullable
    private Waypoint waypoint;

    @Nullable
    public Waypoint getWaypoint() {
        return this.waypoint;
    }

    public boolean hasActiveWaypoint() {
        return waypoint != null;
    }

    public void clearActiveWaypoint() {
        this.waypoint = null;
    }

    public void toggleActiveWaypoint(@Nonnull Waypoint waypoint) {
        if (waypoint.equals(this.waypoint)) {
            this.waypoint = null;
            return;
        }
        this.waypoint = waypoint;
    }

}
