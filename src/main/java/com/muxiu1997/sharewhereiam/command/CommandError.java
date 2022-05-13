package com.muxiu1997.sharewhereiam.command;

import net.minecraft.command.CommandException;

import javax.annotation.Nonnull;

public enum CommandError {
    INVALID_WAYPOINT_CODE,
    ;

    @Nonnull
    public CommandException exception() {
        return new CommandException(LOCALISATION_KEY_PREFIX + name());
    }

    public static final String LOCALISATION_KEY_PREFIX = "sharewhereiam.commanderror.";
}
