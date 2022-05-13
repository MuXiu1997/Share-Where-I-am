package com.muxiu1997.sharewhereiam.client.key;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyShare {
    public static final String KEYBINDING_LOCALISATION_KEY_PREFIX = "sharewhereiam.key.share.";
    public static final KeyBinding INSTANCE = new KeyBinding(
        KEYBINDING_LOCALISATION_KEY_PREFIX + "name",
        Keyboard.KEY_INSERT,
        KEYBINDING_LOCALISATION_KEY_PREFIX + "category"
    );
}
