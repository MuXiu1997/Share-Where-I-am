package com.muxiu1997.sharewhereiam.loader;

import com.muxiu1997.sharewhereiam.integration.Mods;

import java.util.Arrays;

public class IntegrationLoader {

    public static final IntegrationLoader INSTANCE = new IntegrationLoader();

    public void load() {
        Arrays.stream(Mods.values()).forEach(Mods::check);
    }
}
