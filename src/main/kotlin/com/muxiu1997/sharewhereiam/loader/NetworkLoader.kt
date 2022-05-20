package com.muxiu1997.sharewhereiam.loader

import com.muxiu1997.sharewhereiam.network.MessageShareWaypoint
import com.muxiu1997.sharewhereiam.network.MessageStartShareWaypoint
import com.muxiu1997.sharewhereiam.network.network
import cpw.mods.fml.common.network.simpleimpl.IMessage
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler
import cpw.mods.fml.relauncher.Side

object NetworkLoader {
    fun load() {
        register(MessageStartShareWaypoint.Companion.Handler)
        register(MessageShareWaypoint.Companion.Handler)
    }

    private inline fun <reified REQ : IMessage?, REPLY : IMessage?> register(
        messageHandler: IMessageHandler<REQ, REPLY>,
    ) {
        network.registerMessage(messageHandler, REQ::class.java, nextID++, Side.SERVER)
        network.registerMessage(messageHandler, REQ::class.java, nextID++, Side.CLIENT)
    }

    private var nextID = 0
}
