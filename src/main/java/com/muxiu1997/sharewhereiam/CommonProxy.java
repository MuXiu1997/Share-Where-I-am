package com.muxiu1997.sharewhereiam;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        NetworkHandler.instance = new NetworkHandler();
    }
}
