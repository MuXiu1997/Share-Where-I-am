package com.muxiu1997.sharewhereiam.command.base

import com.muxiu1997.sharewhereiam.command.CommandError
import com.muxiu1997.sharewhereiam.util.WaypointBase64.fromBase64
import journeymap.client.model.Waypoint
import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.command.CommandBase
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender

abstract class CommandWaypointBase(private val commandName: String) : CommandBase() {
    abstract val commandUsage: String
    private val commandException: CommandException
        get() = CommandException(commandUsage)

    override fun getCommandName(): String {
        return commandName
    }

    override fun getCommandUsage(sender: ICommandSender): String {
        return commandUsage
    }

    override fun getRequiredPermissionLevel(): Int {
        return 0
    }

    override fun canCommandSenderUseCommand(sender: ICommandSender): Boolean {
        return if (sender !is AbstractClientPlayer) false else super.canCommandSenderUseCommand(sender)
    }


    @Throws(CommandException::class)
    protected fun parseWaypoint(waypointCode: String): Waypoint {
        return try {
            fromBase64(waypointCode)
        } catch (ignored: Exception) {
            throw CommandError.INVALID_WAYPOINT_CODE.exception()
        }
    }

    @Throws(CommandException::class)
    protected fun ensureArgsLength(args: Array<String>?, min: Int, max: Int) {
        val argsLength = args?.size ?: 0
        if (argsLength < min || argsLength > max) throw commandException
    }

    @Throws(CommandException::class)
    protected fun ensureArgsLength(args: Array<String>?, length: Int) {
        val argsLength = args?.size ?: 0
        if (argsLength != length) throw commandException
    }

}
