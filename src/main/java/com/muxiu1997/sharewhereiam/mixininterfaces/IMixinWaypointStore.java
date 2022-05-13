package com.muxiu1997.sharewhereiam.mixininterfaces;

import journeymap.client.model.Waypoint;

public interface IMixinWaypointStore {
    boolean exists(Waypoint waypoint);
}
