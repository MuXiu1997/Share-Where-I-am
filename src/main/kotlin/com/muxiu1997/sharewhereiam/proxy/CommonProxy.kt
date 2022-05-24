package com.muxiu1997.sharewhereiam.proxy

import com.muxiu1997.sharewhereiam.loader.EventLoader
import com.muxiu1997.sharewhereiam.loader.IntegrationLoader
import com.muxiu1997.sharewhereiam.loader.LocalizationLoader
import com.muxiu1997.sharewhereiam.loader.NetworkLoader
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.common.MinecraftForge

open class CommonProxy {
    open fun preInit(event: FMLPreInitializationEvent) {
        LocalizationLoader.load()
    }

    open fun postInit(event: FMLPostInitializationEvent) {
        IntegrationLoader.load()
        EventLoader.load()
        NetworkLoader.load()
    }
}
