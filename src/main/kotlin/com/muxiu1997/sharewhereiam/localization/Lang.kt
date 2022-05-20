package com.muxiu1997.sharewhereiam.localization

import net.minecraft.util.StatCollector

object Lang {
    @Localization(
        prefix = PREFIX_COMMAND,
        en = "Usage: /savewaypoint <waypoint code> [neet to open editor? default: true]"
    )
    lateinit var SAVE_WAYPOINT_USAGE: LocalizedString

    @Localization(
        prefix = PREFIX_COMMAND,
        en = "Waypoint saved"
    )
    lateinit var SAVE_WAYPOINT_SUCCESS: LocalizedString

    @Localization(
        prefix = PREFIX_COMMAND,
        en = "Waypoint exists, can't save"
    )
    lateinit var SAVE_WAYPOINT_EXISTS: LocalizedString

    @Localization(
        prefix = PREFIX_COMMAND,
        en = "Usage: /sharewhereiam"
    )
    lateinit var SHARE_WHERE_I_AM_USAGE: LocalizedString

    @Localization(
        prefix = PREFIX_COMMAND,
        en = "Usage: /toggletempbeacon [waypoint code]"
    )
    lateinit var TOGGLE_TEMP_BEACON_USAGE: LocalizedString

    @Localization(
        prefix = PREFIX_COMMAND_ERROR,
        en = "Invalid waypoint code"
    )
    lateinit var ERROR_INVALID_WAYPOINT_CODE: LocalizedString

    @Localization(
        prefix = PREFIX_CHAT,
        en = "shared a waypoint:"
    )
    lateinit var SHARE_WAYPOINT_MSG: LocalizedString

    @Localization(
        prefix = PREFIX_CHAT,
        en = "SAVE"
    )
    lateinit var SHARE_WAYPOINT_SAVE: LocalizedString

    @Localization(
        prefix = PREFIX_CHAT,
        en = "Save this waypoint to your JourneyMap"
    )
    lateinit var SHARE_WAYPOINT_SAVE_DESC: LocalizedString

    @Localization(
        prefix = PREFIX_CHAT,
        en = "EDIT"
    )
    lateinit var SHARE_WAYPOINT_EDIT: LocalizedString

    @Localization(
        prefix = PREFIX_CHAT,
        en = "Open this Waypoint in Editor"
    )
    lateinit var SHARE_WAYPOINT_EDIT_DESC: LocalizedString

    @Localization(
        prefix = PREFIX_CHAT,
        en = "Enable/Disable"
    )
    lateinit var SHARE_WAYPOINT_TOGGLE: LocalizedString

    @Localization(
        prefix = PREFIX_CHAT,
        en = "Enable/Disable as Temporary Beacon"
    )
    lateinit var SHARE_WAYPOINT_TOGGLE_DESC: LocalizedString

    @Localization(
        prefix = PREFIX_CHAT,
        en = "This is a VisualProspecting Waypoint, you still need to add the Waypoint to the VP in the way that the VP allows"
    )
    lateinit var SHARE_WAYPOINT_VP: LocalizedString

    @Localization(
        prefix = PREFIX_KEYBINDING,
        en = "Share"
    )
    lateinit var KEYBINDING_SHARE_NAME: LocalizedString

    @Localization(
        prefix = PREFIX_KEYBINDING,
        en = "Share Where I am"
    )
    lateinit var KEYBINDING_SHARE_CATEGORY: LocalizedString

    @Localization(
        prefix = PREFIX_TEXT,
        en = "Current Location"
    )
    lateinit var TEXT_DEFAULT_WAYPOINT_NAME: LocalizedString

    @Localization(
        prefix = PREFIX_TEXT,
        en = "Share"
    )
    lateinit var TEXT_JM_WAYPOINT_BUTTON: LocalizedString


    private const val PREFIX_COMMAND = "sharewhereiam.command"
    private const val PREFIX_COMMAND_ERROR = "sharewhereiam.commanderror"
    private const val PREFIX_CHAT = "sharewhereiam.chat"
    private const val PREFIX_KEYBINDING = "sharewhereiam.keybinding"
    private const val PREFIX_TEXT = "sharewhereiam.text"

    data class LocalizedString(val key: String) {
        operator fun invoke(): String = StatCollector.translateToLocal(key)
    }
}


