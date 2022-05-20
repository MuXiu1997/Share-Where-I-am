package com.muxiu1997.sharewhereiam.command

import com.muxiu1997.sharewhereiam.command.base.CommandWaypointBase
import com.muxiu1997.sharewhereiam.integration.journeymap.WaypointManager
import com.muxiu1997.sharewhereiam.localization.Lang
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender

object CommandWaypointToggleTempBeacon : CommandWaypointBase("toggletempbeacon") {
    override val commandUsage = Lang.TOGGLE_TEMP_BEACON_USAGE.key

    @Throws(CommandException::class)
    override fun processCommand(sender: ICommandSender, args: Array<String>) {
        ensureArgsLength(args, 0, 1)
        if (args.isEmpty()) {
            WaypointManager.clearActiveWaypoint()
            return
        }
        val waypoint = parseWaypoint(args[0])
        WaypointManager.toggleActiveWaypoint(waypoint)
    }
}
