package com.muxiu1997.sharewhereiam.command.base;

import com.muxiu1997.sharewhereiam.command.CommandError;
import com.muxiu1997.sharewhereiam.util.WaypointBase64;
import journeymap.client.model.Waypoint;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class CommandWaypointBase extends CommandBase {

    public static final String COMMAND_LOCALISATION_KEY_PREFIX = "sharewhereiam.command.";
    final protected String commandName;

    public CommandWaypointBase(@Nonnull String commandName) {
        super();
        this.commandName = commandName;
    }

    @Override
    @Nonnull
    public String getCommandName() {
        return this.commandName;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        if (!(sender instanceof AbstractClientPlayer)) return false;
        return super.canCommandSenderUseCommand(sender);
    }

    @Nonnull
    public String getCommandUsage() {
        return this.getCommandLocalisationKeyPrefix() + "usage";
    }

    @Override
    @Nonnull
    public String getCommandUsage(ICommandSender sender) {
        return this.getCommandUsage();
    }

    @Nonnull
    protected Waypoint parseWaypoint(@Nonnull String waypointCode) throws CommandException {
        final Waypoint waypoint;
        try {
            waypoint = WaypointBase64.fromBase64(waypointCode);
        } catch (Exception ignored) {
            throw CommandError.INVALID_WAYPOINT_CODE.exception();
        }
        return waypoint;
    }

    protected void ensureArgsLength(@Nullable String[] args, int min, int max) throws CommandException {
        final int argsLength = args != null ? args.length : 0;
        if (argsLength < min || argsLength > max) throw getCommandException();
    }

    protected void ensureArgsLength(@Nullable String[] args, int length) throws CommandException {
        final int argsLength = args != null ? args.length : 0;
        if (argsLength != length) throw getCommandException();
    }

    @Nonnull
    public CommandException getCommandException() {
        return new CommandException(this.getCommandUsage());
    }

    @Nonnull
    public String getCommandLocalisationKeyPrefix() {
        return COMMAND_LOCALISATION_KEY_PREFIX + this.getCommandName() + ".";
    }

    @Nonnull
    public String getCommandLocalisationKey(@Nonnull String key) {
        return this.getCommandLocalisationKeyPrefix() + key;
    }
}
