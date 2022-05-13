package com.muxiu1997.sharewhereiam;

import com.muxiu1997.sharewhereiam.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]", dependencies = "required-after:spongemixins@[1.1.0,);")
public class ShareWhereIAm {

    @SuppressWarnings("unused")
    private static final Logger logger = LogManager.getLogger(Tags.MODID);

    @SidedProxy(clientSide = Tags.GROUPNAME + ".proxy.ClientProxy", serverSide = Tags.GROUPNAME + ".proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
