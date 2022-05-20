package com.muxiu1997.sharewhereiam.integration

import cpw.mods.fml.common.Loader


const val VISUAL_PROSPECTING_MODID = "visualprospecting"

enum class Mods(val modid: String) {
    VISUAL_PROSPECTING(VISUAL_PROSPECTING_MODID),
    ;

    var isLoaded = false
        private set

    fun check() {
        isLoaded = Loader.isModLoaded(modid)
    }
}
