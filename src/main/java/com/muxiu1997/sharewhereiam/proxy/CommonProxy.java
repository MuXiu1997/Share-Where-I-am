package com.muxiu1997.sharewhereiam.proxy;

import com.muxiu1997.sharewhereiam.network.NetworkHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        NetworkHandler.instance = new NetworkHandler();
    }
}
