package com.muxiu1997.sharewhereiam.interfaces;

import journeymap.client.model.Waypoint;

public interface IMixinWaypointStore {
    boolean exists(Waypoint waypoint);
}
