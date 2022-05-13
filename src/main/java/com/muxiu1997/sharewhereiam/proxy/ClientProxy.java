package com.muxiu1997.sharewhereiam.proxy;

import com.muxiu1997.sharewhereiam.loader.CommandLoader;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        CommandLoader.INSTANCE.clientLoad();
    }
}
