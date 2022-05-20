package com.muxiu1997.sharewhereiam.command

import com.muxiu1997.sharewhereiam.command.base.CommandWaypointBase
import com.muxiu1997.sharewhereiam.localization.Lang
import com.muxiu1997.sharewhereiam.mixininterfaces.IMixinWaypointStore
import journeymap.client.ui.UIManager
import journeymap.client.waypoint.WaypointStore
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.util.ChatComponentText

object CommandWaypointSave : CommandWaypointBase("savewaypoint") {
    override val commandUsage = Lang.SAVE_WAYPOINT_USAGE.key

    @Throws(CommandException::class)
    override fun processCommand(sender: ICommandSender, args: Array<String>) {
        ensureArgsLength(args, 1, 2)
        val waypoint = parseWaypoint(args[0])
        val openWaypointEditor = parseOpenWaypointEditor(sender, args)
        if (openWaypointEditor) {
            UIManager.getInstance().openWaypointEditor(waypoint, true, null)
            return
        }
        if ((WaypointStore.instance() as IMixinWaypointStore).exists(waypoint)) {
            sender.addChatMessage(ChatComponentText(Lang.SAVE_WAYPOINT_EXISTS()))
            return
        }
        WaypointStore.instance().add(waypoint)
        sender.addChatMessage(ChatComponentText(Lang.SAVE_WAYPOINT_SUCCESS()))
    }

    @Throws(CommandException::class)
    private fun parseOpenWaypointEditor(sender: ICommandSender, args: Array<String>): Boolean {
        var openWaypointEditor = true
        if (args.size == 2) {
            openWaypointEditor = parseBoolean(sender, args[1])
        }
        return openWaypointEditor
    }
}
