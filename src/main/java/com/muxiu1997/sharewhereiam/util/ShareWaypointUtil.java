package com.muxiu1997.sharewhereiam.util;

import com.muxiu1997.sharewhereiam.network.MessageStartShareWaypoint;
import com.muxiu1997.sharewhereiam.network.NetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import journeymap.client.model.Waypoint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

import java.util.Optional;

@SideOnly(Side.CLIENT)
public class ShareWaypointUtil {
    public static void shareLocation(EntityPlayer player) {
        final Waypoint waypoint = Waypoint.of(player);
        waypoint.setName(StatCollector.translateToLocal("sharewhereiam.text.default_waypoint_name"));
        NetworkHandler.INSTANCE.sendToServer(new MessageStartShareWaypoint(player.getDisplayName(), waypoint));
    }

    public static void addShareWaypointChat(String playerName, Waypoint waypoint, String additionalInformation) {
        final EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        final ChatComponentText chatPartA = new ChatComponentText("");
        chatPartA.appendSibling(new ChatComponentText("[JourneyMap]").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.YELLOW)));
        chatPartA.appendText(" ");
        chatPartA.appendSibling(new ChatComponentText(playerName).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
        chatPartA.appendText(" ");
        chatPartA.appendText(StatCollector.translateToLocal("sharewhereiam.chat.sharewaypoint.sharedawaypoint"));
        final ChatComponentText chatPartB = new ChatComponentText("");
        chatPartB.appendSibling(new ChatComponentText(String.format("%s", waypoint.getName())).setChatStyle(new ChatStyle().setBold(true)));
        chatPartB.appendText(" ");
        chatPartB.appendSibling(new ChatComponentText(String.format("(%d, %d, %d)", waypoint.getX(), waypoint.getY(), waypoint.getZ())).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GRAY)));
        chatPartB.appendText(" ");
        final String waypointBase64 = WaypointBase64.toBase64(waypoint);
        chatPartB.appendSibling(
            new ChatComponentText("[" + StatCollector.translateToLocal("sharewhereiam.chat.sharewaypoint.SAVE") + "]")
                .setChatStyle(
                    new ChatStyle()
                        .setBold(true)
                        .setColor(EnumChatFormatting.BLUE)
                        .setChatHoverEvent(new net.minecraft.event.HoverEvent(net.minecraft.event.HoverEvent.Action.SHOW_TEXT, new ChatComponentText(StatCollector.translateToLocal("sharewhereiam.chat.sharewaypoint.SAVE.desc"))))
                        .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/savewaypoint " + waypointBase64 + " false"))
                )
        );
        chatPartB.appendText(" ");
        chatPartB.appendSibling(
            new ChatComponentText("[" + StatCollector.translateToLocal("sharewhereiam.chat.sharewaypoint.EDIT") + "]")
                .setChatStyle(
                    new ChatStyle()
                        .setBold(true)
                        .setColor(EnumChatFormatting.BLUE)
                        .setChatHoverEvent(new net.minecraft.event.HoverEvent(net.minecraft.event.HoverEvent.Action.SHOW_TEXT, new ChatComponentText(StatCollector.translateToLocal("sharewhereiam.chat.sharewaypoint.EDIT.desc"))))
                        .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/savewaypoint " + waypointBase64))
                )
        );
        chatPartB.appendText(" ");
        chatPartB.appendSibling(
            new ChatComponentText("[" + StatCollector.translateToLocal("sharewhereiam.chat.sharewaypoint.EnableOrDisable") + "]")
                .setChatStyle(
                    new ChatStyle()
                        .setBold(true)
                        .setColor(EnumChatFormatting.BLUE)
                        .setChatHoverEvent(new net.minecraft.event.HoverEvent(net.minecraft.event.HoverEvent.Action.SHOW_TEXT, new ChatComponentText(StatCollector.translateToLocal("sharewhereiam.chat.sharewaypoint.EnableOrDisable.desc"))))
                        .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/toggletempbeacon " + waypointBase64))
                )
        );
        final Optional<IChatComponent> chatPartC = Optional.ofNullable(additionalInformation)
            .filter(s -> !s.isEmpty())
            .map(s -> new ChatComponentText(s).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GRAY)));
        player.addChatComponentMessage(chatPartA);
        player.addChatComponentMessage(chatPartB);
        chatPartC.ifPresent(player::addChatComponentMessage);
    }
}
