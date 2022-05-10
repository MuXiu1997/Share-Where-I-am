package com.muxiu1997.sharewhereiam.command;

import com.muxiu1997.sharewhereiam.journeymap.WaypointManager;
import com.muxiu1997.sharewhereiam.util.WaypointBase64;
import journeymap.client.model.Waypoint;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class CommandToggleTempBeacon extends CommandBase {

    @Override
    public String getCommandName() {
        return "toggletempbeacon";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "sharewhereiam.command.toggletempbeacon.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            WaypointManager.instance.clearActiveWaypoint();
            return;
        }
        if (args.length == 1) {
            final Waypoint waypoint;
            try {
                waypoint = WaypointBase64.fromBase64(args[0]);
            } catch (Exception ignored) {
                throw new CommandException("sharewhereiam.command.toggletempbeacon.usage");
            }
            WaypointManager.instance.toggleActiveWaypoint(waypoint);
            return;
        }
        throw new CommandException("sharewhereiam.command.toggletempbeacon.usage");
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
