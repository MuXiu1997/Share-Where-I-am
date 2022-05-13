package com.muxiu1997.sharewhereiam.command;

import com.muxiu1997.sharewhereiam.command.base.CommandWaypointBase;
import com.muxiu1997.sharewhereiam.util.ShareWaypointUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

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
        ShareWaypointUtil.shareLocation(player);
    }
}
