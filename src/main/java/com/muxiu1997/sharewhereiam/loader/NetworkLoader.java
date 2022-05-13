package com.muxiu1997.sharewhereiam.loader;

import com.muxiu1997.sharewhereiam.network.MessageShareWaypoint;
import com.muxiu1997.sharewhereiam.network.MessageStartShareWaypoint;
import com.muxiu1997.sharewhereiam.network.NetworkHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.relauncher.Side;

public class NetworkLoader {

    public static final NetworkLoader INSTANCE = new NetworkLoader();

    public void load() {
        registerMessage(MessageStartShareWaypoint.Handler.class, MessageStartShareWaypoint.class);
        registerMessage(MessageShareWaypoint.Handler.class, MessageShareWaypoint.class);
    }

    private static int nextID = 0;

    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType) {
        NetworkHandler.INSTANCE.registerMessage(messageHandler, requestMessageType, nextID++, Side.SERVER);
        NetworkHandler.INSTANCE.registerMessage(messageHandler, requestMessageType, nextID++, Side.CLIENT);
    }
}
