package com.muxiu1997.sharewhereiam.proxy;

import com.muxiu1997.sharewhereiam.loader.NetworkLoader;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

public class CommonProxy {
    public void postInit(FMLPostInitializationEvent event) {
        NetworkLoader.INSTANCE.load();
    }
}
