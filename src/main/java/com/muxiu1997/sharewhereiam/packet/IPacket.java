package com.muxiu1997.sharewhereiam.packet;

import io.netty.buffer.ByteBuf;

public interface IPacket {
    void writePayload(final ByteBuf buf);
}
