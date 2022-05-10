package com.muxiu1997.sharewhereiam.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import journeymap.client.model.Waypoint;

public class PacketShareWaypoint implements IPacket {
    public final String playerName;
    public final Waypoint waypoint;

    public PacketShareWaypoint(String playerName, Waypoint waypoint) {
        this.playerName = playerName;
        this.waypoint = waypoint;
    }

    public PacketShareWaypoint(final ByteBuf buf) {
        final int playerNameBufLength = buf.readInt();
        final ByteBuf playerNameBuf = buf.readBytes(playerNameBufLength);
        playerName = playerNameBuf.toString(CharsetUtil.UTF_8);
        final int waypointBufLength = buf.readInt();
        final ByteBuf waypointBuf = buf.readBytes(waypointBufLength);
        waypoint = Waypoint.fromString(waypointBuf.toString(CharsetUtil.UTF_8));
    }

    public void writePayload(final ByteBuf buf) {
        final ByteBuf playerNameBuf = Unpooled.copiedBuffer(playerName, CharsetUtil.UTF_8);
        buf.writeInt(playerNameBuf.readableBytes());
        buf.writeBytes(playerNameBuf, playerNameBuf.readableBytes());
        final ByteBuf waypointBuf = Unpooled.copiedBuffer(waypoint.toString(), CharsetUtil.UTF_8);
        buf.writeInt(waypointBuf.readableBytes());
        buf.writeBytes(waypointBuf, waypointBuf.readableBytes());
    }
}
