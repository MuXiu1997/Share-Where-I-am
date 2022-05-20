@file:JvmName("KeyBinding")
package com.muxiu1997.sharewhereiam.client

import com.muxiu1997.sharewhereiam.localization.Lang
import net.minecraft.client.settings.KeyBinding
import org.lwjgl.input.Keyboard

@JvmField
val KeyShare = KeyBinding(
    Lang.KEYBINDING_SHARE_NAME.key,
    Keyboard.KEY_INSERT,
    Lang.KEYBINDING_SHARE_CATEGORY.key
)
