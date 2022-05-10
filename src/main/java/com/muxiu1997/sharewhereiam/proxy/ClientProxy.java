package com.muxiu1997.sharewhereiam.proxy;

import com.muxiu1997.sharewhereiam.command.CommandSaveWaypoint;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.client.ClientCommandHandler;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ClientCommandHandler.instance.registerCommand(new CommandSaveWaypoint());
    }
}
