package com.muxiu1997.sharewhereiam.network

import cpw.mods.fml.common.network.ByteBufUtils
import cpw.mods.fml.common.network.simpleimpl.IMessage
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler
import cpw.mods.fml.common.network.simpleimpl.MessageContext
import io.netty.buffer.ByteBuf
import journeymap.client.model.Waypoint

class MessageStartShareWaypoint : IMessage {
    lateinit var playerName: String
    lateinit var waypoint: Waypoint
    lateinit var additionalInformation: String

    @Suppress("unused")
    constructor()
    constructor(playerName: String, waypoint: Waypoint) {
        this.playerName = playerName
        this.waypoint = waypoint
        additionalInformation = ""
    }

    constructor(playerName: String, waypoint: Waypoint, additionalInformation: String) {
        this.playerName = playerName
        this.waypoint = waypoint
        this.additionalInformation = additionalInformation
    }

    override fun fromBytes(buf: ByteBuf) {
        playerName = ByteBufUtils.readUTF8String(buf)
        waypoint = Waypoint.fromString(ByteBufUtils.readUTF8String(buf))
        additionalInformation = ByteBufUtils.readUTF8String(buf)
    }

    override fun toBytes(buf: ByteBuf) {
        ByteBufUtils.writeUTF8String(buf, playerName)
        ByteBufUtils.writeUTF8String(buf, waypoint.toString())
        ByteBufUtils.writeUTF8String(buf, additionalInformation)
    }

    companion object {
        object Handler : IMessageHandler<MessageStartShareWaypoint, IMessage?> {
            override fun onMessage(message: MessageStartShareWaypoint, ctx: MessageContext): IMessage? {
                val m = MessageShareWaypoint(message.playerName, message.waypoint, message.additionalInformation)
                if (ctx.side.isClient) {
                    network.sendToServer(m)
                } else {
                    network.sendToAll(m)
                }
                return null
            }
        }
    }
}
