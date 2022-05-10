package com.muxiu1997.sharewhereiam.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import journeymap.client.model.Waypoint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SideOnly(Side.CLIENT)
public class ChatShareWaypoint {
    public static void send(String playerName, Waypoint waypoint) {
        final EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        final ChatComponentText chatPartA = new ChatComponentText("");
        chatPartA.appendSibling(new ChatComponentText("[JourneyMap]").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.YELLOW)));
        chatPartA.appendText(" ");
        chatPartA.appendSibling(new ChatComponentText(playerName).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
        chatPartA.appendText(" shared a waypoint:");
        final ChatComponentText chatPartB = new ChatComponentText("");
        chatPartB.appendSibling(new ChatComponentText(String.format("%s", waypoint.getName())).setChatStyle(new ChatStyle().setBold(true)));
        chatPartB.appendText(" ");
        chatPartB.appendSibling(new ChatComponentText(String.format("(%d, %d, %d)", waypoint.getX(), waypoint.getY(), waypoint.getZ())).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GRAY)));
        chatPartB.appendText(" ");
        chatPartB.appendSibling(
            new ChatComponentText("[SAVE]")
                .setChatStyle(
                    new ChatStyle()
                        .setBold(true)
                        .setColor(EnumChatFormatting.BLUE)
                        .setChatHoverEvent(new net.minecraft.event.HoverEvent(net.minecraft.event.HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Save this waypoint to your JourneyMap")))
                        .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/savewaypoint " + Base64.getEncoder().encodeToString(waypoint.toString().getBytes(StandardCharsets.UTF_8))))
                )
        );
        player.addChatComponentMessage(chatPartA);
        player.addChatComponentMessage(chatPartB);
    }
}
