package com.muxiu1997.sharewhereiam.loader

import com.muxiu1997.sharewhereiam.network.*
import cpw.mods.fml.common.network.simpleimpl.IMessage
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler
import cpw.mods.fml.relauncher.Side

object NetworkLoader {
    fun load() {
        register(MessageShareWaypoint.Companion.Handler)
        register(MessageMarkWaypoint.Companion.Handler)
    }

    private inline fun <reified REQ : IMessage?, REPLY : IMessage?> register(
        messageHandler: IMessageHandler<REQ, REPLY>,
    ) {
        if (messageHandler is IClientSideHandler) network.registerMessage(
            messageHandler,
            REQ::class.java,
            nextID++,
            Side.CLIENT
        )
        if (messageHandler is IServerSideHandler) network.registerMessage(
            messageHandler,
            REQ::class.java,
            nextID++,
            Side.SERVER
        )
    }

    private var nextID = 0
}
