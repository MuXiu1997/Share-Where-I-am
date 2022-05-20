package com.muxiu1997.sharewhereiam.command

import com.muxiu1997.sharewhereiam.localization.Lang
import net.minecraft.command.CommandException

enum class CommandError(private val localizationKey: String) {
    INVALID_WAYPOINT_CODE(Lang.ERROR_INVALID_WAYPOINT_CODE.key);

    fun exception(): CommandException {
        return CommandException(localizationKey)
    }
}
