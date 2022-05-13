package com.muxiu1997.sharewhereiam.network;

import com.muxiu1997.sharewhereiam.util.ShareWaypointUtil;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import journeymap.client.model.Waypoint;

import javax.annotation.Nullable;

public class MessageShareWaypoint implements IMessage {

    public String playerName;
    public Waypoint waypoint;
    public String additionalInformation;

    public MessageShareWaypoint() {
    }

    public MessageShareWaypoint(String playerName, Waypoint waypoint, String additionalInformation) {
        this.playerName = playerName;
        this.waypoint = waypoint;
        this.additionalInformation = additionalInformation;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        playerName = ByteBufUtils.readUTF8String(buf);
        waypoint = Waypoint.fromString(ByteBufUtils.readUTF8String(buf));
        additionalInformation = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, playerName);
        ByteBufUtils.writeUTF8String(buf, waypoint.toString());
        ByteBufUtils.writeUTF8String(buf, additionalInformation);
    }

    public static class Handler implements IMessageHandler<MessageShareWaypoint, IMessage> {
        @Override
        @Nullable
        public IMessage onMessage(MessageShareWaypoint message, MessageContext ctx) {
            if (ctx.side.isServer()) return null;
            ShareWaypointUtil.addShareWaypointChat(message.playerName, message.waypoint, message.additionalInformation);
            return null;
        }
    }
}
