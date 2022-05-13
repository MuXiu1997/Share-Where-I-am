package com.muxiu1997.sharewhereiam.command;

import com.muxiu1997.sharewhereiam.command.base.CommandWaypointBase;
import com.muxiu1997.sharewhereiam.journeymap.WaypointManager;
import journeymap.client.model.Waypoint;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class CommandWaypointToggleTempBeacon extends CommandWaypointBase {

    public static CommandWaypointToggleTempBeacon INSTANCE = new CommandWaypointToggleTempBeacon();

    public CommandWaypointToggleTempBeacon() {
        super("toggletempbeacon");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        ensureArgsLength(args, 0, 1);
        if (args.length == 0) {
            WaypointManager.instance.clearActiveWaypoint();
            return;
        }
        Waypoint waypoint = parseWaypoint(args[0]);
        WaypointManager.instance.toggleActiveWaypoint(waypoint);
    }
}
