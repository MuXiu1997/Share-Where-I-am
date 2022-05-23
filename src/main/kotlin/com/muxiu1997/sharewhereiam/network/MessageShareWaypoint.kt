package com.muxiu1997.sharewhereiam.network

import com.muxiu1997.sharewhereiam.util.ShareWaypointUtil
import cpw.mods.fml.common.network.ByteBufUtils
import cpw.mods.fml.common.network.simpleimpl.IMessage
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler
import cpw.mods.fml.common.network.simpleimpl.MessageContext
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import io.netty.buffer.ByteBuf
import journeymap.client.model.Waypoint

class MessageShareWaypoint : IMessage {
    lateinit var playerName: String
    lateinit var waypointJson: String
    lateinit var additionalInformation: String

    @Suppress("unused")
    constructor()

    constructor(playerName: String, waypointJson: String, additionalInformation: String) {
        this.playerName = playerName
        this.waypointJson = waypointJson
        this.additionalInformation = additionalInformation
    }

    override fun fromBytes(buf: ByteBuf) {
        playerName = ByteBufUtils.readUTF8String(buf)
        waypointJson = ByteBufUtils.readUTF8String(buf)
        additionalInformation = ByteBufUtils.readUTF8String(buf)
    }

    override fun toBytes(buf: ByteBuf) {
        ByteBufUtils.writeUTF8String(buf, playerName)
        ByteBufUtils.writeUTF8String(buf, waypointJson)
        ByteBufUtils.writeUTF8String(buf, additionalInformation)
    }

    companion object {
        object Handler : IMessageHandler<MessageShareWaypoint, IMessage?> {
            override fun onMessage(message: MessageShareWaypoint, ctx: MessageContext): IMessage? {
                if (ctx.side.isServer) return null
                handleClientSideMessage(message)
                return null
            }

            @SideOnly(Side.CLIENT)
            fun handleClientSideMessage(message: MessageShareWaypoint) {
                ShareWaypointUtil.addShareWaypointChat(
                    message.playerName,
                    Waypoint.fromString(message.waypointJson),
                    message.additionalInformation
                )
            }
        }
    }
}
