package com.muxiu1997.sharewhereiam.network

import com.muxiu1997.sharewhereiam.integration.journeymap.WaypointManager
import com.muxiu1997.sharewhereiam.util.WaypointUtil
import cpw.mods.fml.common.network.ByteBufUtils
import cpw.mods.fml.common.network.simpleimpl.IMessage
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler
import cpw.mods.fml.common.network.simpleimpl.MessageContext
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import io.netty.buffer.ByteBuf
import journeymap.client.model.Waypoint

class MessageMarkWaypoint : IMessage {
    lateinit var playerName: String
    lateinit var waypointJson: String

    @Suppress("unused")
    constructor()

    @SideOnly(Side.CLIENT)
    constructor(playerWaypoint: WaypointUtil.PlayerWaypoint) {
        this.playerName = playerWaypoint.player.displayName
        this.waypointJson = playerWaypoint.waypoint.toString()
    }

    override fun fromBytes(buf: ByteBuf) {
        playerName = ByteBufUtils.readUTF8String(buf)
        waypointJson = ByteBufUtils.readUTF8String(buf)
    }

    override fun toBytes(buf: ByteBuf) {
        ByteBufUtils.writeUTF8String(buf, playerName)
        ByteBufUtils.writeUTF8String(buf, waypointJson)
    }

    companion object {
        object Handler : IMessageHandler<MessageMarkWaypoint, IMessage?>, IClientSideHandler, IServerSideHandler {
            override fun onMessage(message: MessageMarkWaypoint, ctx: MessageContext): IMessage? {
                when (ctx.side) {
                    Side.CLIENT -> handleClientSideMessage(message)
                    Side.SERVER -> handleServerSideMessage(message)
                    else -> {}
                }
                return null
            }

            @SideOnly(Side.CLIENT)
            private fun handleClientSideMessage(message: MessageMarkWaypoint) {
                WaypointManager.addTransientBeacon(message.playerName, Waypoint.fromString(message.waypointJson))
            }

            private fun handleServerSideMessage(message: MessageMarkWaypoint) {
                network.sendToAll(message)
            }
        }
    }
}
