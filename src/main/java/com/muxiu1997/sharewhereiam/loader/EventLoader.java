package com.muxiu1997.sharewhereiam.loader;

import com.muxiu1997.sharewhereiam.event.EventHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class EventLoader {
    public static final EventLoader INSTANCE = new EventLoader();

    public void load() {
        MinecraftForge.EVENT_BUS.register(EventHandler.INSTANCE);
        FMLCommonHandler.instance().bus().register(EventHandler.INSTANCE);
    }
}
