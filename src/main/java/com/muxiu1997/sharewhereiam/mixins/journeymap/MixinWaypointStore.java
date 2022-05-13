package com.muxiu1997.sharewhereiam.mixins.journeymap;

import com.google.common.cache.Cache;
import com.muxiu1997.sharewhereiam.mixininterfaces.IMixinWaypointStore;
import journeymap.client.model.Waypoint;
import journeymap.client.waypoint.WaypointStore;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("UnusedMixin")
@Mixin(WaypointStore.class)
public class MixinWaypointStore implements IMixinWaypointStore {
    @SuppressWarnings("UnstableApiUsage")
    @Shadow(remap = false)
    @Final
    private Cache<String, Waypoint> cache;

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public boolean exists(Waypoint waypoint) {
        return this.cache.getIfPresent(waypoint.getId()) != null;
    }
}
