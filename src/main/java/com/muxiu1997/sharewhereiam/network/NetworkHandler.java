package com.muxiu1997.sharewhereiam.network;


import com.muxiu1997.sharewhereiam.Tags;
import com.muxiu1997.sharewhereiam.network.packet.IPacket;
import com.muxiu1997.sharewhereiam.network.packet.PacketShareWaypoint;
import com.muxiu1997.sharewhereiam.network.packet.PacketStartShareWaypoint;
import com.muxiu1997.sharewhereiam.util.ChatShareWaypoint;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;


public class NetworkHandler {
    public static NetworkHandler instance = new NetworkHandler();

    private final String CHANNEL_NAME = Tags.MODID;
    private final FMLEventChannel eventChannel;

    private static final int PacketStartShareWaypointID = 0;
    private static final int PacketShareWaypointID = 1;

    public NetworkHandler() {
        FMLCommonHandler.instance().bus().register(this);
        this.eventChannel = NetworkRegistry.INSTANCE.newEventDrivenChannel(CHANNEL_NAME);
        this.eventChannel.register(this);
    }

    @SubscribeEvent
    public void handlerServerPacket(final ServerCustomPacketEvent event) {
        final ByteBuf payload = event.packet.payload();
        final int packetID = payload.readInt();
        switch (packetID) {
            case PacketStartShareWaypointID:
                final PacketStartShareWaypoint packetStartShareWaypoint = new PacketStartShareWaypoint(payload);
                NetworkHandler.instance.sendToAll(new PacketShareWaypoint(packetStartShareWaypoint.playerName, packetStartShareWaypoint.waypoint));
                break;
            case PacketShareWaypointID:
                break;
        }
    }

    @SubscribeEvent
    public void handlerClientPacket(final ClientCustomPacketEvent event) {
        final ByteBuf payload = event.packet.payload();
        final int packetID = payload.readInt();
        switch (packetID) {
            case PacketStartShareWaypointID:
                final PacketStartShareWaypoint packetStartShareWaypoint = new PacketStartShareWaypoint(payload);
                NetworkHandler.instance.sendToServer(new PacketStartShareWaypoint(packetStartShareWaypoint.playerName, packetStartShareWaypoint.waypoint));
                break;
            case PacketShareWaypointID:
                final PacketShareWaypoint packetShareWaypoint = new PacketShareWaypoint(payload);
                ChatShareWaypoint.send(packetShareWaypoint.playerName, packetShareWaypoint.waypoint);
                break;
        }
    }

    public void sendToAll(final IPacket packet) {
        this.eventChannel.sendToAll(getProxy(packet));
    }

    public void sendToServer(final IPacket packet) {
        this.eventChannel.sendToServer(getProxy(packet));
    }

    private FMLProxyPacket getProxy(final IPacket packet) {
        final ByteBuf buf = Unpooled.buffer();
        if (packet instanceof PacketStartShareWaypoint) {
            buf.writeInt(PacketStartShareWaypointID);
            packet.writePayload(buf);
        } else if (packet instanceof PacketShareWaypoint) {
            buf.writeInt(PacketShareWaypointID);
            packet.writePayload(buf);
        } else {
            throw new IllegalStateException("Unknown packet type: " + packet.getClass());
        }
        return new FMLProxyPacket(buf, CHANNEL_NAME);
    }
}
