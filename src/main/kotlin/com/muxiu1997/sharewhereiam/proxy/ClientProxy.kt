package com.muxiu1997.sharewhereiam.proxy

import com.muxiu1997.sharewhereiam.loader.CommandLoader
import com.muxiu1997.sharewhereiam.loader.KeyLoader
import cpw.mods.fml.common.event.FMLPostInitializationEvent

class ClientProxy : CommonProxy() {
    override fun postInit(event: FMLPostInitializationEvent) {
        super.postInit(event)
        CommandLoader.clientLoad()
        KeyLoader.clientLoad()
    }
}
