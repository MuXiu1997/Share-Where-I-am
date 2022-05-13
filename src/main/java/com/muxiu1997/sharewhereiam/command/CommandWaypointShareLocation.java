package com.muxiu1997.sharewhereiam.command;

import com.muxiu1997.sharewhereiam.command.base.CommandWaypointBase;
import com.muxiu1997.sharewhereiam.network.NetworkHandler;
import com.muxiu1997.sharewhereiam.network.packet.PacketStartShareWaypoint;
import journeymap.client.model.Waypoint;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class CommandWaypointShareLocation extends CommandWaypointBase {

    public static CommandWaypointShareLocation INSTANCE = new CommandWaypointShareLocation();

    public CommandWaypointShareLocation() {
        super("sharewhereiam");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        ensureArgsLength(args, 0);
        assert sender instanceof EntityPlayer;
        EntityPlayer player = (EntityPlayer) sender;
        final Waypoint waypoint = Waypoint.of(player);
        waypoint.setName(StatCollector.translateToLocal(getCommandLocalisationKey("default_waypoint_name")));
        NetworkHandler.instance.sendToServer(new PacketStartShareWaypoint(player.getDisplayName(), waypoint));
    }
}
