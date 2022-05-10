package com.muxiu1997.sharewhereiam.proxy;

import com.muxiu1997.sharewhereiam.journeymap.WaypointManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(WaypointManager.instance);
    }
}
