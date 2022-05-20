package com.muxiu1997.sharewhereiam.loader

import com.muxiu1997.sharewhereiam.command.CommandWaypointSave
import com.muxiu1997.sharewhereiam.command.CommandWaypointShareLocation
import com.muxiu1997.sharewhereiam.command.CommandWaypointToggleTempBeacon
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraftforge.client.ClientCommandHandler

object CommandLoader {
    @SideOnly(Side.CLIENT)
    fun clientLoad() {
        ClientCommandHandler.instance.registerCommand(CommandWaypointSave)
        ClientCommandHandler.instance.registerCommand(CommandWaypointShareLocation)
        ClientCommandHandler.instance.registerCommand(CommandWaypointToggleTempBeacon)
    }
}
