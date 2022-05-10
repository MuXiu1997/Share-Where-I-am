package com.muxiu1997.sharewhereiam.command;

import com.muxiu1997.sharewhereiam.network.NetworkHandler;
import com.muxiu1997.sharewhereiam.network.packet.PacketStartShareWaypoint;
import journeymap.client.model.Waypoint;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandShareWhereIAm extends CommandBase {
    @Override
    public String getCommandName() {
        return "sharewhereiam";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "sharewhereiam.command.sharewhereiam.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 0) throw new CommandException("sharewhereiam.command.sharewhereiam.usage");
        if (!(sender instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) sender;
        final Waypoint waypoint = Waypoint.of(player);
        waypoint.setName("Current Location");
        NetworkHandler.instance.sendToServer(new PacketStartShareWaypoint(player.getDisplayName(), waypoint));
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
