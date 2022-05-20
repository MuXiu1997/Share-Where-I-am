package com.muxiu1997.sharewhereiam.command

import com.muxiu1997.sharewhereiam.command.base.CommandWaypointBase
import com.muxiu1997.sharewhereiam.localization.Lang
import com.muxiu1997.sharewhereiam.util.ShareWaypointUtil
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayer

object CommandWaypointShareLocation : CommandWaypointBase("sharewhereiam") {
    override val commandUsage = Lang.SHARE_WHERE_I_AM_USAGE.key

    @Throws(CommandException::class)
    override fun processCommand(sender: ICommandSender, args: Array<String>) {
        ensureArgsLength(args, 0)
        assert(sender is EntityPlayer)
        val player = sender as EntityPlayer
        ShareWaypointUtil.shareLocation(player)
    }
}
