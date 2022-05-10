package com.muxiu1997.sharewhereiam.command;

import journeymap.client.model.Waypoint;
import journeymap.client.ui.UIManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CommandSaveWaypoint extends CommandBase {
    @Override
    public String getCommandName() {
        return "savewaypoint";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "command.savewaypoint.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1)
            throw new CommandException("command.savewaypoint.usage");
        String waypointCode = args[0];
        final String waypointJSON = new String(Base64.getDecoder().decode(waypointCode), StandardCharsets.UTF_8);
        Waypoint waypoint = Waypoint.fromString(waypointJSON);
        UIManager.getInstance().openWaypointEditor(waypoint, true, null);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_canCommandSenderUseCommand_1_) {
        return true;
    }
}
