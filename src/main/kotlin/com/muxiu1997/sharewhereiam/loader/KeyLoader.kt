package com.muxiu1997.sharewhereiam.loader

import com.muxiu1997.sharewhereiam.client.KeyShare
import cpw.mods.fml.client.registry.ClientRegistry
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
object KeyLoader {
    fun clientLoad() {
        ClientRegistry.registerKeyBinding(KeyShare)
    }
}
