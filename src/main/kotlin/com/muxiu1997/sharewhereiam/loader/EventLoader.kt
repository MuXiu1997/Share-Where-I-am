package com.muxiu1997.sharewhereiam.loader

import com.muxiu1997.sharewhereiam.event.EventHandler
import cpw.mods.fml.common.FMLCommonHandler
import net.minecraftforge.common.MinecraftForge

object EventLoader {
    fun load() {
        MinecraftForge.EVENT_BUS.register(EventHandler)
        FMLCommonHandler.instance().bus().register(EventHandler)
    }
}
