package com.muxiu1997.sharewhereiam.integration;

import cpw.mods.fml.common.Loader;

public enum Mods {
    VISUAL_PROSPECTING("visualprospecting"),
    ;

    public final String modid;
    private boolean loaded;

    Mods(String modid) {
        this.modid = modid;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void check() {
        this.loaded = Loader.isModLoaded(modid);
    }
}
