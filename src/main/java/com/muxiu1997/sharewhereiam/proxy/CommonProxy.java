package com.muxiu1997.sharewhereiam.proxy;

import com.muxiu1997.sharewhereiam.journeymap.WaypointManager;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(WaypointManager.instance);
    }
}
