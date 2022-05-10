package com.muxiu1997.sharewhereiam.util;

import journeymap.client.model.Waypoint;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class WaypointBase64 {
    public static String toBase64(Waypoint waypoint) {
        final String waypointJSON = waypoint.toString();
        return Base64.getEncoder().encodeToString(waypointJSON.getBytes(StandardCharsets.UTF_8));
    }

    public static Waypoint fromBase64(String base64) {
        final String waypointJSON = new String(Base64.getDecoder().decode(base64), StandardCharsets.UTF_8);
        return Waypoint.fromString(waypointJSON);
    }
}
