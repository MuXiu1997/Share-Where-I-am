package com.muxiu1997.sharewhereiam.command;

import com.muxiu1997.sharewhereiam.interfaces.IMixinWaypointStore;
import com.muxiu1997.sharewhereiam.util.WaypointBase64;
import journeymap.client.model.Waypoint;
import journeymap.client.ui.UIManager;
import journeymap.client.waypoint.WaypointStore;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CommandSaveWaypoint extends CommandBase {
    @Override
    public String getCommandName() {
        return "savewaypoint";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "sharewhereiam.command.savewaypoint.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (2 < args.length) throw new CommandException("sharewhereiam.command.savewaypoint.usage");
        if (!(sender instanceof EntityPlayer)) return;
        final Waypoint waypoint = parseWaypoint(args);
        boolean openWaypointEditor = parseOpenWaypointEditor(sender, args);
        if (openWaypointEditor) {
            UIManager.getInstance().openWaypointEditor(waypoint, true, null);
            return;
        }
        if (((IMixinWaypointStore) WaypointStore.instance()).exists(waypoint)) {
            sender.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("sharewhereiam.command.savewaypoint.exists")));
            return;
        }
        WaypointStore.instance().add(waypoint);
        sender.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("sharewhereiam.command.savewaypoint.success")));
    }

    private boolean parseOpenWaypointEditor(ICommandSender sender, String[] args) {
        boolean openWaypointEditor = true;
        if (args.length == 2) {
            openWaypointEditor = CommandBase.parseBoolean(sender, args[1]);
        }
        return openWaypointEditor;
    }

    private Waypoint parseWaypoint(String[] args) {
        if (args.length < 1) throw new CommandException("sharewhereiam.command.savewaypoint.usage");
        final Waypoint waypoint;
        try {
            waypoint = WaypointBase64.fromBase64(args[0]);
        } catch (Exception ignored) {
            throw new CommandException("sharewhereiam.command.savewaypoint.usage");
        }
        return waypoint;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
